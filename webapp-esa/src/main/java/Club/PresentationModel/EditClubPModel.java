package Club.PresentationModel;


import Util.AuthenticationManager;
import Club.DataModel.Club;
import AppUser.Leader.DataModel.Leader;
import Club.Service.ClubServiceInterface;
import AppUser.Leader.Service.LeaderServiceInterface;
import AppUser.Leader.PresentationModel.ListClubsPModel;
import Util.Pages;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import org.primefaces.model.UploadedFile;

@ViewScoped
@Named

public class EditClubPModel implements Serializable {

    private static final long serialVersionUID = 2815796004558360299L;

    @Inject
    AuthenticationManager am;
    @Inject
    private ClubServiceInterface clubService;
    @Inject LeaderServiceInterface leaderService;

    @Inject
    private ListClubsPModel listClubsController;
    private Club selectedClub;
    private boolean isADDMode;

    private UploadedFile file;
    
    @PostConstruct
    void init(){
        System.out.println("Iniiiiiiit");
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        String mode = params.get("Mode");
        if (mode.equals("EDIT")){
            isADDMode = false;
            String clubid = params.get("club_id");
            int club_id = Integer.parseInt(clubid);
            selectedClub = clubService.getClubById(club_id);
            System.out.println("teeeeeeeeeeeeeeeeeeeeeeeeeeest"+selectedClub.getTitle());
        }else{
            isADDMode= true;
            selectedClub = new Club();
        }
        
    }

    public Club getSelectedClub() {
        return selectedClub;
    }

    public void setSelectedClub(Club selectedClub) {
        this.selectedClub = selectedClub;
    }

    public String doSave() {
        

        //clubProducer.getSelectedClub().setLeader(entityManager.find(Leader.class, 1));
        if (isADDMode) {
            Integer leader=am.getCurrentUserId();
            if(leader != null){
            clubService.addClub(selectedClub, leader);
            listClubsController.updateClubList();
            }

        }else{
            clubService.updateClub(selectedClub);
            //slistClubsController.updateClubList();
            
        }
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

    public void uploadPicture() {
        OutputStream out = null;
        System.out.println("filename" + file);
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            System.out.println("real-path" + context.getRealPath("/resources/img"));
            File targetFolder = new File(context.getRealPath("/resources/img"));
            InputStream inputStream = file.getInputstream();
            out = new FileOutputStream(new File(targetFolder, selectedClub.getTitle() + ".png"));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditClubPModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditClubPModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(EditClubPModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
