package Opensource_SW_Project.Project.apiPayload.exception.handler;

import Opensource_SW_Project.Project.apiPayload.code.BaseErrorCode;
import Opensource_SW_Project.Project.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}
