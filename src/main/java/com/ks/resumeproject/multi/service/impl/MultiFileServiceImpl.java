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

@Service
@RequiredArgsConstructor
public class MultiFileServiceImpl implements MultiFileService {

    private final MultiFileMapper multiFileMapper;

    @Override
    public BigInteger setFile(MultiFormDto form) {

        BigInteger result;

        result = multiFileMapper.getFileId();

        int file_sub_title = 0;

        List files = form.getFiles();

        for(Object file : files){
            try {
                MultipartFile f = (MultipartFile) file;

                String fileName = UUID.randomUUID().toString();

                FileDto fileDto = new FileDto();
                fileDto.setFile(f.getBytes());
                fileDto.setFileType(form.getType());
                fileDto.setFileNm(fileName);
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


}
