package com.ks.resumeproject.multi.repository;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface MultiFileMapper {

    void setFile(FileDto fileDto);

    BigInteger getFileId();

    List<FileDto> getFileList(BigInteger fileId);

    void setProfile(AccountDto account);
}
