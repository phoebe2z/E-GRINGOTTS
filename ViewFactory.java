/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class ViewFactory {
    private AccountType loginAccountType;
    
    private final ObjectProperty<UserMenuOption> userSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionView;
    private AnchorPane exchangeView;
    private AnchorPane cardDetailView;
    private AnchorPane editCardDetailView;
    private AnchorPane editUserProfileView;
    
    private AnchorPane marauderView;
    private AnchorPane editProfileButtonView;
    private AnchorPane changeUserPassPinButtonView;
    
    private final ObjectProperty<AdminMenuOption> adminSelectedMenuItem;
    private AnchorPane adminDashboardView;
    private AnchorPane adminDepositView;
    private AnchorPane adminUserView;
    private AnchorPane adminUserCardView;
    private AnchorPane adminAddCurrencyView;
    private AnchorPane adminEditProfileView;
    
    
    
    
    
    public ViewFactory(){
        this.loginAccountType = AccountType.USER;
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }
    
    //USER
    
    public void showUserWindow(){
        FXMLLoader loader  = new FXMLLoader(EGringgots.class.getResource("/FXMLFiles/User.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);
        createStage(loader);
    }
    
    
    public ObjectProperty<UserMenuOption> getUserSelectedMenuItem(){
        return userSelectedMenuItem;
    }
    
    public AnchorPane getDashboardView(){
        if (dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/FXMLFiles/Dashboard.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    
    public AnchorPane getExchangeView(){
        if (exchangeView == null){
            try{
                exchangeView = new FXMLLoader(getClass().getResource("/FXMLFiles/Exchange.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return exchangeView;
    }
    
    public AnchorPane getTransactionView(){
        if (transactionView == null){
            try{
                transactionView = new FXMLLoader(getClass().getResource("/FXMLFiles/Transaction.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return transactionView;
    }
    
    public AnchorPane getCardDetailView(){
        if (cardDetailView == null){
            try{
                cardDetailView = new FXMLLoader(getClass().getResource("/FXMLFiles/CardDetail.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return cardDetailView;
    }
    
        public AnchorPane getEditCardDetailView(){
        if (editCardDetailView == null){
            try{
                editCardDetailView = new FXMLLoader(getClass().getResource("/FXMLFiles/EditCardButton.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return editCardDetailView;
    }
    
    public AnchorPane getEditUserProfileView(){
        if (editUserProfileView == null){
            try{
                editUserProfileView = new FXMLLoader(getClass().getResource("/FXMLFiles/EditProfile.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return editUserProfileView;
    }
    
    public AnchorPane getMarauderView(){
        if (marauderView == null){
            try{
                marauderView = new FXMLLoader(getClass().getResource("/FXMLFiles/Transfer.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return marauderView;
    }
    
    //ADMIN   
    public void showAdminWindow(){
        FXMLLoader loader  = new FXMLLoader(EGringgots.class.getResource("/FXMLFiles/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }
        
    public ObjectProperty<AdminMenuOption> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }
    
    public AnchorPane getAdminDashboardView(){
        if (adminDashboardView == null){
            try{
                adminDashboardView = new FXMLLoader(getClass().getResource("/FXMLFiles/AdminDashboard.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return adminDashboardView;
    }
        
    public AnchorPane getAdminUserView(){
        if (adminUserView == null){
            try{
                adminUserView = new FXMLLoader(getClass().getResource("/FXMLFiles/ViewUser.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return adminUserView;
    }
    
    public AnchorPane getAdminDepositView(){
        if (adminDepositView == null){
            try{
                adminDepositView = new FXMLLoader(getClass().getResource("/FXMLFiles/Deposit.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return adminDepositView;
    }
    
        public AnchorPane getAdminAddCurrencyView(){
        if (adminAddCurrencyView == null){
            try{
                adminAddCurrencyView = new FXMLLoader(getClass().getResource("/FXMLFiles/AddCurrency.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return adminAddCurrencyView;
    }
    
    public AnchorPane getAdminUserCardView(){
        if (adminUserCardView == null){
            try{
                adminUserCardView = new FXMLLoader(getClass().getResource("/FXMLFiles/UserCardDetail.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return adminUserCardView;
    }
    
        public AnchorPane getAdminEditProfileView(){
        if (adminEditProfileView == null){
            try{
                adminEditProfileView = new FXMLLoader(getClass().getResource("/FXMLFiles/EditProfile.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return adminEditProfileView;
    }
    
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/Login.fxml"));
        createStage(loader);
    }
    
    public void showRegisterWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/RegisterPage.fxml"));
        createStage(loader);
    }
    
    
    void showVerificationWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/VerificationPage.fxml"));
        createStage(loader);
    }

    void showAccCreationWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/AccountCreation.fxml"));
        createStage(loader);
    }
    
    public void closeStage(Stage stage){
        stage.close();
    }

        
    private void createStage(FXMLLoader loader) {
        Scene scene = null;

        try {
            scene = new Scene(loader.load());

        } catch (Exception e) {
        }

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("E-Gringgots");
        stage.show();

    }

    public AnchorPane getEditUserProfileButton() {
        if (editProfileButtonView == null){
            try{
                editProfileButtonView = new FXMLLoader(getClass().getResource("/FXMLFiles/EditProfileButton.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return editProfileButtonView;
    }

    public AnchorPane getChangeUserPassPinButton() {
        if (changeUserPassPinButtonView == null){
            try{
                changeUserPassPinButtonView = new FXMLLoader(getClass().getResource("/FXMLFiles/ChangePassPinButton.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return changeUserPassPinButtonView;
    }


}
