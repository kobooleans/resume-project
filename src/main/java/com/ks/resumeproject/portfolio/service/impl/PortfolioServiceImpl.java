package com.ks.resumeproject.portfolio.service.impl;

import com.ks.resumeproject.error.domain.ErrorDto;
import com.ks.resumeproject.error.exception.CustomCodeException;
import com.ks.resumeproject.error.exception.CustomException;
import com.ks.resumeproject.multi.service.MultiFileService;
import com.ks.resumeproject.portfolio.domain.*;
import com.ks.resumeproject.portfolio.repository.PortfolioMapper;
import com.ks.resumeproject.portfolio.service.PortfolioService;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final SecurityService securityService;
    private final PortfolioMapper portfolioMapper;
    private final MultiFileService multiFileService;
    private final SecurityUtil securityUtil;

    @Override
    public List<CategoryDto> categoryList(AccountDto accountDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());
        return portfolioMapper.categoryList(accountDto);
    }

    @Override
    public void insertCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 id를 가지고 온다.
         * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        AccountContext accountContext = securityUtil.getAccount();

        if(categoryDto.getId() == null){
            categoryDto.setId(accountContext.getAccountDto().getId());
        }

        portfolioMapper.insertCategory(categoryDto);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 id를 가지고 온다.
         * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        AccountContext accountContext = securityUtil.getAccount();

        if(categoryDto.getId() == null){
            categoryDto.setId(accountContext.getAccountDto().getId());
        }

        portfolioMapper.updateCategory(categoryDto);
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) {
        /*사용자 Id가 없는 경우를 대비해 토큰에 있는 id를 가지고 온다.
         * 토큰의 암호화를 풀기위해 SecurityContextHolder 사용 */
        AccountContext accountContext = securityUtil.getAccount();

        if(categoryDto.getId() == null){
            categoryDto.setId(accountContext.getAccountDto().getId());
        }
        /*이미 사용 중인 카테고리인지 확인하는 쿼리*/
        int categoryCnt = portfolioMapper.selectCategoryCnt(categoryDto);

        if(categoryCnt > 0){
            throw new CustomCodeException(new ErrorDto("IS_REFERENCE_VALUE", "이미 사용되고 있는 카테고리는 삭제할 수 없습니다."));
        }else{
            portfolioMapper.deleteCategory(categoryDto);
        }
    }

    @Override
    public List<PortfolioDto> portfolioAllList(PortfolioDto portfolioDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(portfolioDto.getUsername());
        portfolioDto.setId(account.getId());

        return portfolioMapper.portfolioAllList(portfolioDto);
    }

    @Override
    @Transactional
    public void insertPortfolio(PortfolioDto portfolioDto) {
        AccountContext accountContext = securityUtil.getAccount();

        if(portfolioDto.getId() == null){
            portfolioDto.setId(accountContext.getAccountDto().getId());
        }
        /*port_id Max값 조회*/
        BigInteger maxPortId = portfolioMapper.selectMaxPortId(portfolioDto);

        portfolioDto.setPortId(maxPortId);

        if(portfolioDto.getCategoryId().equals(new BigInteger("0"))){
            portfolioDto.setCategoryId(null);
        }

        portfolioMapper.insertPortfolio(portfolioDto);

        for(PortfolioDetailDto dto : portfolioDto.getDetailList()){
            if(dto.getDetailTitle() != null && dto.getDetailContent() != null){
                dto.setPortId(maxPortId);
                dto.setId(portfolioDto.getId());
                dto.setRandomId(portfolioDto.getRandomId());
                /*상세 구분이 필요 없어졌지만 not null이라서 공백 set*/
                dto.setDetailDivision("");

                portfolioMapper.insertPortfolioDetail(dto);
            }
        }
        /*skill_id Max값 조회*/
        BigInteger maxSkillId = portfolioMapper.selectMaxSkillId(portfolioDto);
        portfolioDto.setSkillId(maxSkillId);
        portfolioDto.setSkillTitle("");

        if(!portfolioDto.getSkills().isEmpty()){
            portfolioMapper.insertPortSkill(portfolioDto);

            for(PortfolioSkillDetailDto skillDto : portfolioDto.getSkills()){
                skillDto.setSkillId(maxSkillId);
                skillDto.setId(portfolioDto.getId());
                skillDto.setRandomId(portfolioDto.getRandomId());
                skillDto.setPortId(portfolioDto.getPortId());
                portfolioMapper.insertPortSkillDetail(skillDto);
            }
        }

        /*이미지 update*/
        updatePortfolioImg(portfolioDto);
        /* 첨부파일 update */
        updatePortAttachFile(portfolioDto);
    }

    private void updatePortfolioImg(PortfolioDto portfolioDto) {
        AccountContext accountContext = securityUtil.getAccount();
        AccountDto accountDto = accountContext.getAccountDto();
/*
        if(portfolioDto.getId() == null){
            portfolioDto.setId(accountDto.getId());
        }
*/

        /*포트폴리오 img_file_id update */
        int count = portfolioMapper.updatePortfolioImg(portfolioDto);

        if(count > 0){
            accountDto.setFileId(portfolioDto.getImgFileId());

            multiFileService.setProfile(accountDto);
        }
    }

    public void updatePortAttachFile(PortfolioDto portfolioDto){
        AccountContext accountContext = securityUtil.getAccount();
        AccountDto accountDto = accountContext.getAccountDto();

        int fileCnt = portfolioMapper.getAttachFileCnt(portfolioDto);

        //파일이 있으면 Y처리
        if(fileCnt > 0){
            /*포트폴리오 file_id update */
            int count = portfolioMapper.updatePortAttachFile(portfolioDto);

            if(count > 0){
                accountDto.setFileId(portfolioDto.getFileId());

                multiFileService.setProfile(accountDto);
            }
        }else{
            //파일이 없다면 null 처리
            portfolioMapper.updateAttachFileToNull(portfolioDto);
        }

    }

    @Override
    public PortfolioDto portfolioDetailList(PortfolioDto portfolioDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(portfolioDto.getUsername());
        portfolioDto.setId(account.getId());

        PortfolioDto portDto = portfolioMapper.portfolioList(portfolioDto);
        portDto.setDetailList(portfolioMapper.portfolioDetailList(portfolioDto));

        if(portDto.getFileId() != null){
            portDto.setFileList(portfolioMapper.fileList(portDto));
        }

        BigInteger skillId = portfolioMapper.selectPortSkillId(portfolioDto);
        portDto.setSkillId(skillId);
        portfolioDto.setSkillId(skillId);
        portDto.setSkills(portfolioMapper.portSkillDetailList(portfolioDto));

        return portDto;
    }

    @Override
    @Transactional
    public void updatePortfolioDetail(PortfolioDto portfolioDto) {
        AccountContext accountContext = securityUtil.getAccount();

        if(portfolioDto.getId() == null){
            portfolioDto.setId(accountContext.getAccountDto().getId());
        }

        if(portfolioDto.getCategoryId().equals(new BigInteger("0"))){
            portfolioDto.setCategoryId(null);
        }

        /*사용하지 않는 이미지 use_yn N처리*/
        portfolioMapper.updatePortFileUseYn(portfolioDto);

        portfolioMapper.updatePortfolio(portfolioDto);

        /*추가 내용 부분 전체 삭제 후 재 등록*/
        portfolioMapper.deletePortfolioDetailAll(portfolioDto);

        for(PortfolioDetailDto dto : portfolioDto.getDetailList()){
            if(!dto.getDetailTitle().isEmpty() && !dto.getDetailContent().isEmpty()){
                dto.setId(portfolioDto.getId());
                dto.setRandomId(portfolioDto.getRandomId());
                dto.setPortId(portfolioDto.getPortId());
                /*상세 구분이 필요 없어졌지만 not null이라서 공백 set*/
                dto.setDetailDivision("");

                portfolioMapper.insertPortfolioDetail(dto);
            }
        }

        /*메서드화 하기, 우클릭 - 리팩토링 - 메서드 추출*/
        /*스킬 전체 삭제 후 재등록*/
        portfolioMapper.deletePortSkillDetailAll(portfolioDto);
        portfolioMapper.deletePortSkill(portfolioDto);

        /*skill_id Max값 조회*/
        BigInteger maxSkillId = portfolioMapper.selectMaxSkillId(portfolioDto);
        portfolioDto.setSkillId(maxSkillId);
        portfolioDto.setSkillTitle("");

        portfolioMapper.insertPortSkill(portfolioDto);

        for(PortfolioSkillDetailDto skillDto : portfolioDto.getSkills()){
            skillDto.setSkillId(portfolioDto.getSkillId());
            skillDto.setId(portfolioDto.getId());
            skillDto.setRandomId(portfolioDto.getRandomId());
            skillDto.setPortId(portfolioDto.getPortId());

            portfolioMapper.insertPortSkillDetail(skillDto);
        }

        updatePortfolioImg(portfolioDto);
        /* 첨부파일 update */
        updatePortAttachFile(portfolioDto);
    }

    @Override
    @Transactional
    public void deletePortfolio(PortfolioDto portfolioDto) {
        AccountContext accountContext = securityUtil.getAccount();

        if(portfolioDto.getId() == null) {
            portfolioDto.setId(accountContext.getAccountDto().getId());
        }

        /*포트폴리오 스킬 삭제*/
        portfolioMapper.deletePortSkillDetailAll(portfolioDto);
        portfolioMapper.deletePortSkill(portfolioDto);

        /*포트폴리오 삭제*/
        portfolioMapper.deletePortfolioDetailAll(portfolioDto);
        portfolioMapper.deletePortfolio(portfolioDto);

        portfolioMapper.updateFileUseYn(portfolioDto);
    }

    @Override
    public List<PortfolioSkillDetailDto> selectSkillDtailDtos(PortfolioDto portfolioDto) {
        AccountDto account = securityService.selectUserAccount(portfolioDto.getUsername());
        portfolioDto.setId(account.getId());

        return portfolioMapper.selectSkillDtailDtos(portfolioDto);
    }
}

