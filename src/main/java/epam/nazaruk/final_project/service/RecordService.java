package epam.nazaruk.final_project.service;

import epam.nazaruk.final_project.db.dao.MasterDAO;
import epam.nazaruk.final_project.db.dao.ServiceDAO;
import epam.nazaruk.final_project.db.dao.ServiceRecordDAO;
import epam.nazaruk.final_project.db.entity.Master;
import epam.nazaruk.final_project.db.entity.Service;
import epam.nazaruk.final_project.db.entity.ServiceRecord;
import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.db.exception.AlreadyExistRecordException;
import epam.nazaruk.final_project.service.exception.AlreadyExistRecordExceptionService;

public class RecordService {


        public static void recordCreation(String chosenProcedure, String chosenMaster, String date, String timeslot, User user) throws AlreadyExistRecordExceptionService {

            Master master = new MasterDAO().findMasterByName(chosenMaster.split(" ")[0], chosenMaster.split(" ")[1]);
            Service service = new ServiceDAO().findServiceByName(chosenProcedure);

            ServiceRecord serviceRecord = new ServiceRecord();
            serviceRecord.setDate(date);
            serviceRecord.setUserId(user.getId());
            serviceRecord.setStatusId(1);
            serviceRecord.setMastersId(master.getId());
            serviceRecord.setServiceId(service.getId());
            serviceRecord.setTimeslot(timeslot);
            serviceRecord.setUserRolesId(user.getRoleId());

            ServiceRecordDAO serviceRecordsDAO = new ServiceRecordDAO();
            try {
                serviceRecordsDAO.insertIntoServiceRecords(serviceRecord);
            } catch (AlreadyExistRecordException throwables) {
                throw new AlreadyExistRecordExceptionService();
            }
        }
    }
