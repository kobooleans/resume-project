package com.ks.resumeproject.multi.service;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.multi.domain.MultiFormDto;
import com.ks.resumeproject.security.domain.AccountDto;
import jakarta.validation.Valid;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface MultiFileService {

    BigInteger setFile(MultiFormDto form);

    List<FileDto> getFileList(BigInteger fileId);

    void setProfile(AccountDto account);

    BigInteger modifyFile(MultiFormDto form);

    BigInteger deleteFile(MultiFormDto fileId);

    FileDto inputImage(@Valid MultiFormDto form);

    byte[] downloadFile(String fileId);
}
