package com.ks.resumeproject.batch.writer;

import com.ks.resumeproject.batch.repository.BatchMapper;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.security.domain.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@RequiredArgsConstructor
public class AccountWriter implements ItemWriter<AccountDto> {

    private final BatchMapper batchMapper;

    @Override
    public void write(Chunk<? extends AccountDto> items) throws Exception {
        for(AccountDto accountDto : items){
            //데이터를 삭제하는 로직을 추가한다.
            batchMapper.deleteResumeEdu(accountDto);
            batchMapper.deleteResumeCareer(accountDto);
            batchMapper.deleteResumeActivity(accountDto);
            batchMapper.deleteResumeSkillDetail(accountDto);
            batchMapper.deleteResumeSkill(accountDto);
            batchMapper.deleteResumeLangTest(accountDto);
            batchMapper.deleteResumeLicense(accountDto);
            batchMapper.deleteResumeAward(accountDto);
            batchMapper.deleteResumeCoverLetter(accountDto);
            batchMapper.deleteResumeInfo(accountDto);
            batchMapper.deleteResume(accountDto);
            batchMapper.deletePortfolioSkillDetail(accountDto);
            batchMapper.deletePortfolioSkill(accountDto);
            batchMapper.deletePortfolioDetail(accountDto);

            //포트폴리오에 등록한 이미지 및 첨부파일
            List<PortfolioDto> portfolioDtoList = batchMapper.selectDelPortFile(accountDto);
            if(!portfolioDtoList.isEmpty()){
                for(PortfolioDto portfolioDto : portfolioDtoList){

                    batchMapper.deletePortImgFile(portfolioDto);
                    batchMapper.deletePortFile(portfolioDto);
                }
            }

            //프로필의 이미지
            batchMapper.deleteFile(accountDto.getFileId());

            batchMapper.deletePortfolio(accountDto);
            batchMapper.deleteCategory(accountDto);
            batchMapper.deleteMainInfo(accountDto);
            batchMapper.deleteAccountMyPage(accountDto);
            batchMapper.deleteAccount(accountDto);
        }
    }
}