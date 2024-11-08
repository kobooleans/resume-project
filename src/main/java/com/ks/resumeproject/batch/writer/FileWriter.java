package com.ks.resumeproject.batch.writer;

import com.ks.resumeproject.batch.repository.BatchMapper;
import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
public class FileWriter implements ItemWriter<FileDto> {

    private final BatchMapper batchMapper;

    @Override
    public void write(Chunk<? extends FileDto> items) throws Exception {
        //데이터를 삭제하는 로직을 추가한다.
        for(FileDto fileDto : items) {
            batchMapper.deleteFile(fileDto.getFileId());
        }
    }
}