package com.ks.resumeproject.multi.service.impl;

import com.ks.resumeproject.multi.domain.MultiFormDto;
import com.ks.resumeproject.multi.repository.MultiFileMapper;
import com.ks.resumeproject.multi.service.MultiFileService;
import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MultiFileServiceImpl implements MultiFileService {

    private final MultiFileMapper multiFileMapper;

    @Override
    public BigInteger setFile(MultiFormDto form) {

        BigInteger result;

        result = multiFileMapper.getFileId();

        //file_sub_id 는 1부터
        int file_sub_title = 1;

        List files = form.getFiles();

        for(Object file : files){
            try {
                MultipartFile f = (MultipartFile) file;

                FileDto fileDto = new FileDto();
                fileDto.setFile(f.getBytes());
                fileDto.setFileType(f.getContentType());
                fileDto.setFileNm(f.getOriginalFilename());
                fileDto.setUseYn("N");
                fileDto.setFileId(result);
                fileDto.setFileSubId(file_sub_title);
                multiFileMapper.setFile(fileDto);

                file_sub_title++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public List<FileDto> getFileList(BigInteger fileId) {
        return multiFileMapper.getFileList(fileId);
    }

    @Override
    public void setProfile(AccountDto account) {
        multiFileMapper.setProfile(account);
    }

    @Override
    public BigInteger modifyFile(MultiFormDto form) {
        BigInteger result;

        if(form.getFileId().equals(new BigInteger("0"))){
            result = multiFileMapper.getFileId();
        }else{
            result = form.getFileId();
        }

        if(form.getFileSubId() != null){
            form.setFileSubIds(Stream.of(form.getFileSubId().split(",")).mapToInt(Integer::parseInt).toArray());

            multiFileMapper.deleteFile(form);
        }

        //파일 삭제만하고 추가 하지 않는 경우가 있을 수 있다.
        if(form.getFiles() != null){
            List files = form.getFiles();

            int file_sub_id = multiFileMapper.getMaxFileSubId(result);

            for(Object file : files){
                try {
                    MultipartFile f = (MultipartFile) file;

                    FileDto fileDto = new FileDto();
                    fileDto.setFile(f.getBytes());
                    fileDto.setFileType(f.getContentType());
                    fileDto.setFileNm(f.getOriginalFilename());
                    fileDto.setUseYn("N");
                    fileDto.setFileId(result);
                    fileDto.setFileSubId(file_sub_id);
                    multiFileMapper.setFile(fileDto);

                    file_sub_id++;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    @Override
    public BigInteger deleteFile(MultiFormDto form) {
        BigInteger result;

        result = form.getFileId();
        multiFileMapper.deleteFileAll(form);

        return result;
    }

    @Override
    public FileDto inputImage(MultiFormDto form) {

        BigInteger result;

        result = multiFileMapper.getFileId();

        //file_sub_id 는 1부터
        int file_sub_title = 1;

        FileDto fileDto = new FileDto();

        try {
            MultipartFile f = (MultipartFile) form.getFiles().get(0);


            fileDto.setFile(f.getBytes());
            fileDto.setFileType(f.getContentType());
            fileDto.setFileNm(f.getOriginalFilename());
            fileDto.setUseYn("N");
            fileDto.setFileId(result);
            fileDto.setFileSubId(file_sub_title);
            multiFileMapper.setFile(fileDto);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileDto;
    }
}
