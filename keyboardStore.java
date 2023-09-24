//package Keyboard_Store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class keyboardStore extends Application {

	static Stage stg; // Set stage global
	Scene LOGIN, REGISTER, ADMIN, USER, CATALOGUE;
	static Scene VIEW_IMAGE;
	TextField emailF, emailFRegist;
	PasswordField pwF, pwFRegist, confirmPWF;
	ArrayList<String> nameData = new ArrayList<String>();
	ArrayList<String> passData = new ArrayList<String>();
	
	
	// Login Window
	public void loginWindow() {
		// Label utama
		Label JKStore = new Label("Jee Keyboard Store");
		JKStore.setFont(Font.font("Jee Keyboard Store", FontWeight.BOLD, 30));
		JKStore.setAlignment(Pos.TOP_CENTER);
		
		// Label email dan password
		Label email = new Label("Email");
		Label password = new Label("Password");
		email.setFont(Font.font(15));
		password.setFont(Font.font(15));
		
		// Text field untuk email dan password
		emailF = new TextField();
		pwF = new PasswordField();
		
		// Button untuk register dan login
		Button regist = new Button("Register");
		Button login = new Button("Login");
		regist.setStyle("-fx-background-color: #555555; -fx-text-fill: #e5f0f5;");
		login.setStyle("-fx-background-color: #555555; -fx-text-fill: #e5f0f5;");
		regist.setPrefSize(120, 30);
		login.setPrefSize(120, 30);
		
		// Set action event for buttons
		regist.setOnAction(e -> {
			registerWindow(stg);
		});
		
		login.setOnAction(e -> {
			String EMAIL = emailF.getText();
			String PASSWORD = pwF.getText();
			Alert warn = new Alert(AlertType.NONE);
			
			nameData = new ArrayList<String>();
			passData = new ArrayList<String>();
			
			if(EMAIL.isEmpty()) {
				warn.setAlertType(AlertType.ERROR);
				warn.setTitle("Login Failed");
				warn.setHeaderText("");
				warn.setContentText("Email field can't be empty");
				warn.show();
				return;
			}
			else if(PASSWORD.isEmpty()) {
				warn.setAlertType(AlertType.ERROR);
				warn.setTitle("Login Failed");
				warn.setHeaderText("");
				warn.setContentText("Password field can't be empty");
				warn.show();
				return;
			}
			else if(EMAIL.equals("admin") && PASSWORD.equals("admin")) {
				try {
					AdminWindow(stg);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				warn.setAlertType(AlertType.INFORMATION);
				warn.setTitle("Admin Login Success");
				warn.setHeaderText("");
				warn.setContentText("Welcome ADMIN");
				warn.show();
				return;
			}
			else {
//				nameData.add(EMAIL);
//				passData.add(PASSWORD);
				try {
					userWindow(stg);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				warn.setAlertType(AlertType.INFORMATION);
				warn.setTitle("Login Success");
				warn.setHeaderText("");
				warn.setContentText("Welcome " + EMAIL);
				warn.show();
				return;
			}
		});
		
		// Buat horizontal box
		HBox HB = new HBox ();
		HB.getChildren().addAll(regist, login);
		HB.setSpacing(10.0);
		
		// Buat gridpane
		GridPane GP = new GridPane();
		GP.add(email, 0, 0);
		GP.add(password, 0, 1);
		GP.add(emailF, 1, 0);
		GP.add(pwF, 1, 1);
		GP.add(HB, 1, 2);
		GP.setHgap(5);
		GP.setVgap(7);
		GP.setAlignment(Pos.CENTER);
		
		// Buat vertical box
		VBox VB = new VBox(JKStore, GP);
		VB.setSpacing(20);
		VB.setAlignment(Pos.CENTER);
		
		// Masukkan vBox kedalam Borderpane
		BorderPane borderP = new BorderPane(VB);
		borderP.setCenter(VB);
		
		// Masukkan kedalam scene
		LOGIN = new Scene(borderP, 800, 500);
		
		stg.setScene(LOGIN);
		stg.setTitle("Login");
		stg.show();
	}
	
	// Register window
	public void registerWindow(Stage stg) {
		// Label utama
			Label RegisterLBL = new Label("Register");
			RegisterLBL.setFont(Font.font("Jee Keyboard Store", FontWeight.BOLD, 30));
			RegisterLBL.setAlignment(Pos.TOP_CENTER);
				
			// Label email dan password
			Label emailRegist = new Label("Email");
			Label passwordRegist = new Label("Password");
			Label confirmPW = new Label("Confirm Password");
			emailRegist.setFont(Font.font(15));
			passwordRegist.setFont(Font.font(15));
			confirmPW.setFont(Font.font(15));
				
			// Text field untuk email dan password
			emailFRegist = new TextField();
			pwFRegist = new PasswordField();
			confirmPWF = new PasswordField();
				
			// Button untuk register dan login
			Button registBTN = new Button("Register");
			Button loginBTN = new Button("Login");
			registBTN.setStyle("-fx-background-color: #555555; -fx-text-fill: #e5f0f5;");
			loginBTN.setStyle("-fx-background-color: #555555; -fx-text-fill: #e5f0f5;");
			registBTN.setPrefSize(120, 30);
			loginBTN.setPrefSize(120, 30);
				
			// Set action event for buttons
			loginBTN.setOnAction(e -> {
				loginWindow();
			});
			
			String emailDATA = emailFRegist.getText();
			String passwordDATA = pwFRegist.getText();
			String cpasswordDATA = confirmPWF.getText();
			Alert warn = new Alert(AlertType.NONE);
			
			registBTN.setOnAction(e -> {
				if(emailDATA.isEmpty() || !emailDATA.endsWith("@email.com")|| emailDATA.indexOf('@') != emailDATA.lastIndexOf('@')|| emailDATA.contains(" ") || emailDATA.startsWith("@") || passwordDATA.isEmpty() || cpasswordDATA.isEmpty() || !passwordDATA.equals(cpasswordDATA)) {
					warn.setAlertType(AlertType.ERROR);
					warn.setTitle("Error");
					warn.setHeaderText("");
					warn.setContentText("Invalid Email, Password, or Confirm Password");
					return;
				}
				else {
					loginWindow();
				}
			});
				
			// Buat horizontal box
			HBox HBregist = new HBox ();
			HBregist.getChildren().addAll(loginBTN, registBTN);
			HBregist.setSpacing(10.0);
				
			// Buat gridpane
			GridPane GP = new GridPane();
			GP.add(emailRegist, 0, 0);
			GP.add(passwordRegist, 0, 1);
			GP.add(confirmPW, 0, 2);
			GP.add(emailFRegist, 1, 0);
			GP.add(pwFRegist, 1, 1);
			GP.add(confirmPWF, 1, 2);
			GP.add(HBregist, 1, 3);
			GP.setHgap(5);
			GP.setVgap(7);
			GP.setAlignment(Pos.CENTER);
			
			// Buat vertical box
			VBox VB = new VBox(RegisterLBL, GP);
			VB.setSpacing(20);
			VB.setAlignment(Pos.CENTER);
				
			// Masukkan vBox kedalam Borderpane
			BorderPane borderP = new BorderPane(VB);
			borderP.setCenter(VB);
				
			// Masukkan kedalam scene
			REGISTER = new Scene(borderP, 800, 500);
				
			stg.setScene(REGISTER);
			stg.setTitle("Register");
			stg.show();
		}

	// Manage Keyboard Window
	public void AdminWindow(Stage stg) throws FileNotFoundException {
		BorderPane BP = new BorderPane();
		MenuBar menubtn = new MenuBar();
		Menu MENU = new Menu("Menu");
		MenuItem logout = new MenuItem("Logout");
		logout.setOnAction(e -> {
			loginWindow();
		});
		
		// masukkan menu item ke dalam menu
		MENU.getItems().add(logout);
		menubtn.getMenus().add(MENU);
		
		// membuat list produk
		
		Image img1 = new Image(new FileInputStream("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard1.png"));
		Image img2 = new Image(new FileInputStream("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard2.png"));
		Image img3 = new Image(new FileInputStream("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard3.png"));
		Image img4 = new Image(new FileInputStream("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard4.png"));
		
		VBox VB = new VBox();
		
		HBox HB = new HBox(ProductDetail(img1, "Produc 1", "Price 1", "Stock 1", "Desc 1"));
		HBox HB2 = new HBox(ProductDetail(img2, "Product 2", "Price 2", "Stock 2", "Desc 2"));
		HBox HB3 = new HBox(ProductDetail(img3, "Product 2", "Price 2", "Stock 2", "Desc 2"));
		HBox HB4 = new HBox(ProductDetail(img4, "Product 2", "Price 2", "Stock 2", "Desc 2"));
			
		VB.getChildren().addAll(HB, HB2, HB3, HB4);
		
		// Membuat scroll bar
		ScrollPane SB = new ScrollPane();
		SB.setPrefSize(595, 200);
		SB.setContent(VB);
		
		// Masukkan menu bar ke dalam borderpane
		BP.setTop(menubtn);
		BP.setCenter(SB);
		
		// Masukkan ke dalam scene
		ADMIN = new Scene(BP, 800, 500);
		stg.setScene(ADMIN);
		stg.setTitle("ADMIN");
		stg.show();
	}
	
	// Detail produk pada admin page
	public static HBox ProductDetail(Image img, String productName, String productPrice, String productStock, String productDesc) {
	// Membuat detail produk
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(150);
		imgView.setFitWidth(200);
		
		Label name = new Label("Name");
		Label price = new Label("Price");
		Label stock = new Label("Stock");
		Label desc = new Label("Description");
				
		TextField productNameField = new TextField();
		productNameField.setPromptText(productName);
		TextField productPriceField = new TextField();
		productPriceField.setPromptText(productPrice);
		TextField productStockField = new TextField();
		productStockField.setPromptText(productStock);
		TextField productDescField = new TextField();
		productDescField.setPromptText(productDesc);
		
		// Disable all the text fields
		productNameField.setDisable(true);
		productPriceField.setDisable(true);
		productStockField.setDisable(true);
		productDescField.setDisable(true);
		
		productName = productNameField.getText().toString();
		productPrice = productPriceField.getText().toString();
		productStock = productStockField.getText().toString();
		productDesc = productDescField.getText();
		
		Button update = new Button("Update");
		
		update.setOnAction(e ->{
			// Enable all the text fields
			productNameField.setDisable(false);
			productPriceField.setDisable(false);
			productStockField.setDisable(false);
			productDescField.setDisable(false);
		});
		
		imgView.setOnMouseClicked(e -> {
			viewImageWindow(imgView, "Desc", stg);
		});
		
		GridPane GP = new GridPane();
		GP.add(name, 0, 0);
		GP.add(productNameField, 1, 0);
		GP.add(price, 2, 0);
		GP.add(productPriceField, 3, 0);
		GP.add(stock, 4, 0);
		GP.add(productStockField, 5, 0);
		GP.add(desc, 0, 1);
		GP.add(productDescField, 1, 1);
		GP.add(update, 1, 2);
		
		HBox HB = new HBox();
		HB.getChildren().addAll(imgView, GP);
	
		return HB;
	}
	
	public static void viewImageWindow(ImageView imgView,String desc, Stage stg) {
		Text productDesc = new Text(desc);
//		productDesc.setFont(Font.font(desc, FontWeight.BOLD, 30));
		
		Button right = new Button("Rotate Right");
		Button left = new Button("Rotate Left");
		Button in = new Button("Zoom In");
		Button out = new Button("Zoom Out");
		
		// Set action buttons
		right.setOnAction(e -> {
			Rotate r = new Rotate();
			r.setPivotX(imgView.getBoundsInLocal().getWidth()/2);
			r.setPivotY(imgView.getBoundsInLocal().getWidth()/2);
			r.setAngle(90);
			imgView.getTransforms().add(r);
		});
		
		left.setOnAction(e -> {
			Rotate r = new Rotate();
			r.setPivotX(imgView.getBoundsInLocal().getWidth()/2);
			r.setPivotY(imgView.getBoundsInLocal().getWidth()/2);
			r.setAngle(-90);
			imgView.getTransforms().add(r);
		});
		
		in.setOnAction(e -> {
			Scale s = new Scale();
			s.setPivotX(imgView.getBoundsInLocal().getWidth()/2);
			s.setPivotY(imgView.getBoundsInLocal().getWidth()/2);
			s.setX(1.1);
			s.setY(1.1);
			
			imgView.getTransforms().add(s);
		});
		
		out.setOnAction(e -> {
			Scale s = new Scale();
			s.setPivotX(imgView.getBoundsInLocal().getWidth()/2);
			s.setPivotY(imgView.getBoundsInLocal().getWidth()/2);
			s.setX(0.9);
			s.setY(0.9);
			
			imgView.getTransforms().add(s);
		});
		
		right.setPrefSize(100, 30);
		left.setPrefSize(100, 30);
		in.setPrefSize(100, 30);
		out.setPrefSize(100, 30);
		
		GridPane GP = new GridPane();
		GP.add(right, 0, 0);
		GP.add(left, 0, 1);
		GP.add(in, 1, 0);
		GP.add(out, 1, 1);
		
		VBox VB = new VBox();
		VB.getChildren().addAll(imgView, productDesc, GP);
		VB.setAlignment(Pos.CENTER);
		
		VIEW_IMAGE = new Scene(VB, 500, 300);
		stg = new Stage();
		stg.setTitle("View Image");
		stg.setScene(VIEW_IMAGE);
		stg.show();
	}
	
	public void userWindow(Stage stg) throws MalformedURLException {
		Text title = new Text("NEW KEYBOARD ARRIVED!");
		title.setFont(Font.font("NEW KEYBOARD ARRIVED!", FontWeight.BOLD, 30));

		Button continues = new Button("Continue >>");
		continues.setStyle("-fx-background-color: #555555; -fx-text-fill: #e5f0f5;");
		MenuBar menubtn = new MenuBar();
		Menu MENU = new Menu("Menu");
		MenuItem viewKey = new MenuItem("View Keyboard");
		MenuItem logout = new MenuItem("Logout");
		logout.setOnAction(e -> {
			loginWindow();
		});
		
		viewKey.setOnAction(e -> {
			try {
				catalogue();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		continues.setOnAction(e -> {
			try {
				catalogue();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		// masukkan menu item ke dalam menu
		MENU.getItems().addAll(viewKey, logout);
		menubtn.getMenus().add(MENU);
			
		File file;
		file = new File("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard.mp4");
		Media media = new Media(file.toURI().toURL().toString());
		MediaPlayer MP = new MediaPlayer(media);
		MediaView MV = new MediaView(MP);
		MV.setFitHeight(200);
		MV.setFitWidth(300);
		
		MP.play();
		
		VBox VB = new VBox();
		VB.getChildren().addAll(title, MV, continues);
		VB.setAlignment(Pos.CENTER);
		
		BorderPane BP = new BorderPane();
		BP.setTop(menubtn);
		BP.setCenter(VB);
		
		USER = new Scene(BP, 800, 600);
		stg = new Stage();
		stg.setTitle("USER");
		stg.setScene(USER);
		stg.show();
	}
	
	public void catalogue() throws FileNotFoundException {
		MenuBar menubtn = new MenuBar();
		Menu MENU = new Menu("Menu");
		MenuItem logout = new MenuItem("Logout");
		logout.setOnAction(e -> {
			loginWindow();
		});
		MENU.getItems().add(logout);
		menubtn.getMenus().add(MENU);
		
		Label keyCat = new Label("Keyboard Catalogue");
		
		VBox VB = new VBox();
		VB.getChildren().addAll(menubtn, keyCat);
		
		Image img1 = new Image(new FileInputStream("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard1.png"));
		Image img2 = new Image(new FileInputStream("C:\\Users\\jemyn\\eclipse-workspace\\JFX\\src\\assets\\keyboard2.png"));
//		ImageView imgView = new ImageView(img1);
		VBox VBitems = new VBox();
		
		VBox VBitem1 = new VBox(ProductDetails(img1, "Keyboard 1", "800000", "56", "Desc"));
//		HBox HB = new HBox();
//		HB.getChildren().addAll(imgView, ProductDetail("Product1", 800000, 56, "Classic black and yellow"));
		VBitems.getChildren().add(VBitem1);
//		VBitems.getChildren().add(HB2);
		
		// Your Cart
		Text urCart = new Text("Your Cart");
		urCart.setFont(Font.font(30));
		
//		Text txt1 = new Text("");
		Button buy = new Button("Buy");
		Button clear = new Button("Clear Cart");
		
		BorderPane smallBP = new BorderPane();
		VBox VBbuyClear = new VBox();
		VBbuyClear.getChildren().addAll(buy, clear);
		smallBP.setTop(urCart);
		smallBP.setBottom(VBbuyClear);
		
		// Masukkan menu bar ke dalam borderpane
		BorderPane BP = new BorderPane();
		BP.setTop(menubtn);
		BP.setLeft(VBitems);
		
		
		CATALOGUE = new Scene(BP, 800, 600);
		stg = new Stage();
		stg.setTitle("USER");
		stg.setScene(CATALOGUE);
		stg.show();
	}

	public static VBox ProductDetails(Image img, String productName, String productPrice, String productStock, String productDesc) {
		// Membuat detail produk
			ImageView imgView = new ImageView(img);
			imgView.setFitHeight(150);
			imgView.setFitWidth(200);
			
			Label name = new Label("Name: " + productName);
			Label price = new Label("Price: " + productPrice);
			Label stock = new Label("Stock: " + productStock);
			Label desc = new Label("Description: " + productDesc);
			
			imgView.setOnMouseClicked(e -> {
				viewImageWindow(imgView, productDesc, stg);
			});

			VBox VBitem = new VBox();
			VBox VBcat = new VBox();
			
			VBcat.getChildren().addAll(name, price, stock, desc);
			VBitem.getChildren().addAll(imgView, VBcat);
		
			return VBitem;
		}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stg = new Stage();
		stg = primaryStage;
		loginWindow();
	}

}















