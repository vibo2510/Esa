package com.mycompany.webapp.esa.controller;


import com.mycompany.webapp.esa.services.ClubService;
import com.mycompany.webapp.esa.data.ClubProducer;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@SessionScoped
@Named

public class EditClubController implements Serializable {
	private static final long serialVersionUID = 2815796004558360299L;


	@Inject
	private ClubProducer clubProducer;
        @Inject 
        private ClubServiceInterface clubService;
        
        @Inject 
        private ListClubsController listClubsController;
        
        private UploadedFile file;
        
       
	public String doSave() {
                    //Solange noch keine Login vorhanden, wird hier Standard-Leader setzten
                    Leader leader = new Leader();
                    leader.setId(1);
                    leader.setFirstname("Hans");
                    leader.setLastname("Wurst");
                    leader.setEmail("hw@web.de");
                    
                    clubProducer.getSelectedClub().setLeader(leader);
		if (clubProducer.isAddMode()) {

                    
                    clubService.addClub(clubProducer.getSelectedClub());
                    listClubsController.updateClubList();
                                        
                    //ohne DB
                    //clubListProducer.getClubs().add(clubProducer.getSelectedClub());
		}
                clubService.updateClub(clubProducer.getSelectedClub());
		return Pages.LIST_CLUBS;
	}
	public String doCancel() {
		return Pages.LIST_CLUBS;
	}
        
        public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
        public  void uploadPicture(){
            OutputStream out= null;
            System.out.println("filename"+file);
            try {
                ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
                System.out.println("real-path"+context.getRealPath("/resources/img"));
                File targetFolder = new File(context.getRealPath("/resources/img"));
                InputStream inputStream = file.getInputstream();
                out = new FileOutputStream(new File(targetFolder, clubProducer.getSelectedClub().getTitle()+".png"));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }   inputStream.close();
                out.flush();
                out.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditClubController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EditClubController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(EditClubController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    
    }
        }
