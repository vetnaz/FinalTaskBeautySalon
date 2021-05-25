package epam.nazaruk.final_project.service;

import epam.nazaruk.final_project.db.bean.MasterRecordsBean;
import epam.nazaruk.final_project.db.dao.MasterRecordsDAO;

import java.util.List;

public class AdminService {
    public static  int getNumberOfRows(){
        MasterRecordsDAO dao = new MasterRecordsDAO();

        return dao.selectCountOfRows();
    }

    public static List<MasterRecordsBean> findMasterRecords(int page, int recordsPerPage){
        int row = (page - 1) * recordsPerPage;
        MasterRecordsDAO dao = new MasterRecordsDAO();

        return dao.selectAllRecords(row,recordsPerPage);
    }

}
