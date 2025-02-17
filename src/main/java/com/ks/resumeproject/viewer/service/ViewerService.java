package com.ks.resumeproject.viewer.service;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.viewer.domain.Viewer;

public interface ViewerService {


    String selectRandomId(AccountDto accountDto);

    Viewer cvProjectViewerList(AccountDto accountDto);
}
