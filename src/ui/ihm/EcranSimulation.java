package ui.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import algo.Composition.Epice;
import algo.Composition.Fruits;
import algo.Composition.Houblon;
import algo.Composition.Ingredient;
import algo.Composition.Levures;
import algo.Composition.Malt;
import algo.Composition.Sucre;
import ui.cd.ControleurDialogue;
import ui.cd.ControleurDonnees;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EcranSimulation extends BorderPane {

	private static ControleurDialogue cDialogue = null;
	// On donne un nom a cette interface pour permettre l'identification par le
	// systeme
	private static final EcransApplication nameScreen = EcransApplication.SIMULATION;

	// DEFINITION DU BORDER
	
	Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(1), new Insets(5)));
	
	/*
	Border test = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
			new BorderWidths(1), new Insets(5)));
	*/

	//Image sourceImage = new Image("./Ressources/Images/ressources.png");
	//Image cuve = new Image("./Ressources/Images/cuve.png");
	//Image paramettres = new Image("./Ressources/Images/paramettres.png");
	//Image infos = new Image("./Ressources/Images/informations.png");
	//Image feu = new Image("./Ressources/Images/chaud.png");
	
	Image tank = new Image ("./Ressources/Images/cuve.png");
	
	
	
	private BorderPane bpTop;
	private HBox hbButtonsSettings;
	
	private Button bInfo;
	private static Label lTimer;
	
	/*
	private VBox vbTemperature;
	private Label lFireTemperature;
	private Slider sTemperature;
	*/
	
	private VBox vbTank;
	private StackPane spTank;
	ImageView ivTank;
	
	private VBox vbTankTest;
	private VBox vbTankTop;
	private static Label lTemperature;
	private VBox vbTankBottom;
	private VBox vbFire;
	
	private VBox vbManagement;
	private HBox hvManagementBottom;
	private VBox vbButtonsAndFire;
	private VBox vbButtons;
	private static HBox hbButtonsL1;
	private static HBox hbButtonsL2;
	
	private HBox hbTime;
	private Label lTemps;
	private Button speedOne;
	private Button speedFour;
	private Button speedSixteen;
	private Button speedSixtyFour;
	private Button speedOneHundredTwentyEight;
	private Button speedFiveThousand;
	
	private BorderPane bpTimer;
	private VBox vbTimer;
	private Button bAddAlert;
	
	private Timer timer;
	
	private VBox vbIngrediens;
	
	private BorderPane bpAddElement;
	private Label lAddElement;
	private Button bLeaveAddElement;
	private HBox hbQuantity;
	private Label lQuantity;
	private TextField tfQuantity;
	private Button bValiderAddElement;
	private VBox vbAddElementTop;
	private VBox vbAddElementRight;
	private VBox vbAddElementCenter;
	private VBox vbAddElementBottom;
	
	private BorderPane bpAddTimer;
	private Label lAddTimer;
	private Button bLeaveAddTimer;
	private VBox vbCenterAddTimer;
	private HBox hbHeures;
	private TextField tfHeures;
	private Label lHeures;
	private HBox hbMinutes;
	private TextField tfMinutes;
	private Label lMinutes;
	private Button bValiderAddTimer;
	private VBox vbAddTimerTop;
	private VBox vbAddTimerRight;
	private VBox vbAddTimerCenter;
	private VBox vbAddTimerBottom;
	private static Label message;;
	
	private HBox hbFire;
	private Button bOnOffFire;
	private static Label lPowerFire;
	private Label lUniteFire;
	private int powerFire = 0;
	private boolean fireOn = false;

	private VBox vbPopUp;
	private Label lContenu;
	private static VBox vbContenu;

	// On crée les differents composants de l'interface (Label, Button)
	private void createWidgets() throws FileNotFoundException {
		
	}

	// On crée les differents emplacements de l'interface (HBox, VBox), cela
	// permet de structurer la page
	private void createContainers() {
		


		// CREATION ET CONFIGURATION DE LA BORDERPANE TOP
		bpTop = new BorderPane();
		
		/*
		//CREATION ET CONFIGURATION DE LA VBOX POUR LA TEMPERATURE
		vbTemperature = new VBox();
		vbTemperature.setAlignment(Pos.CENTER);
		lFireTemperature = new Label("100°C");
		lFireTemperature.setTextFill(Paint.valueOf("#ffffff"));
		sTemperature = new Slider();
		sTemperature.setOrientation(Orientation.VERTICAL);
		sTemperature.setPrefHeight(400);
		*/
		
		//CREATION ET CONFIGURATION DE LA VBOX POUR LA CUVE
		vbTank = new VBox();
		//vbTank.setBorder(test);
		vbTank.setAlignment(Pos.BOTTOM_CENTER);
		//vbTank.setMaxWidth(600);
		lTemperature = new Label("00°C");
		lTemperature.setTranslateY(95);
		lTemperature.setFont(Font.font(30));
		message = new Label();
		
		// CUVE SANS IMAGE
		vbTankTest = new VBox();
		vbTankTest.setBorder(border);
		vbTankTest.setAlignment(Pos.BOTTOM_CENTER);
		vbTankTest.setMaxWidth(300);
		vbTankTest.setPrefHeight(400);
		vbTankTest.setStyle("-fx-background-color: lightGrey");
		vbTankTop = new VBox();
		vbTankTop.setPrefHeight(400);
		vbTankBottom = new VBox();
		vbTankBottom.setPrefHeight(50);
		vbFire = new VBox();
		vbFire.setPrefHeight(30);
		vbFire.setAlignment(Pos.BOTTOM_CENTER);
		
		// CUVE AVEC IMAGE
		spTank = new StackPane();
		//spTank.setBorder(test);
		spTank.setMaxWidth(600);
		ivTank = new ImageView(tank);
		ivTank.setFitHeight(600);
		ivTank.setFitWidth(450);
		
		//CREATION ET CONFIGURATION DE LA VBOX POUR LA GESTION
		vbManagement = new VBox();
		//vbManagement.setBorder(test);
		//vbManagement.setMaxWidth(1000);
		hvManagementBottom = new HBox();
		//hvManagementBottom.setBorder(test);
		vbButtonsAndFire = new VBox();
		vbButtons = new VBox();
		//vbButtons.setBorder(test);
		vbButtons.setSpacing(10);
		vbButtons.setPrefWidth(600);
		vbButtons.setAlignment(Pos.CENTER_LEFT);
		hbButtonsL1 = new HBox();
		hbButtonsL1.setSpacing(10);
		hbButtonsL1.setAlignment(Pos.CENTER_LEFT);
		hbButtonsL2 = new HBox();
		hbButtonsL2.setSpacing(10);
		hbButtonsL2.setAlignment(Pos.CENTER_LEFT);
		hbButtonsSettings = new HBox();
		hbButtonsSettings.setTranslateY(5);
		hbButtonsSettings.setTranslateX(5);
		hbButtonsSettings.setSpacing(5);
		
		bInfo = new Button("information");
		bInfo.setPrefSize(110, 40);
		lTimer = new Label("00:00");
		lTimer.setTextFill(Paint.valueOf("#ffffff"));
		lTimer.setFont(Font.font(40));
		lTimer.setTranslateX(-10);
		bAddAlert = new Button("ajouter une alerte");
		
		//CREATION ET CONFIGURATION DE LA HBOX POUR LA GESTION DU TEMPS
		hbTime = new HBox();
		hbTime.setPrefHeight(100);
		hbTime.setAlignment(Pos.CENTER);
		hbTime.setSpacing(30);
		lTemps = new Label("temps");
		lTemps.setTextFill(Paint.valueOf("#ffffff"));
		lTemps.setFont(Font.font(20));
		speedOne = new Button("x1");
		speedOne.setPrefSize(50, 50);
		colorButton(speedOne);
		speedFour = new Button("x4");
		speedFour.setPrefSize(50, 50);
		colorButton(speedFour);
		speedSixteen = new Button("x16");
		speedSixteen.setPrefSize(50, 50);
		colorButton(speedSixteen);
		speedSixtyFour = new Button("x64");
		speedSixtyFour.setPrefSize(50, 50);
		colorButton(speedSixtyFour);
		speedOneHundredTwentyEight = new Button("x128");
		speedOneHundredTwentyEight.setPrefSize(50, 50);
		colorButton(speedOneHundredTwentyEight);
		speedFiveThousand = new Button("x5000");
		speedFiveThousand.setPrefSize(80, 50);
		colorButton(speedFiveThousand);


		
		//CREATION ET CONFIGURATION DE LA VBOX POUR L'AFFICHAGE DES TIMER
		bpTimer = new BorderPane();
		bpTimer.setTranslateX(-5);
		vbTimer = new VBox();
		vbTimer.setPrefHeight(430);
		vbTimer.setPrefWidth(200);
		vbTimer.setSpacing(5);
		vbTimer.setAlignment(Pos.TOP_RIGHT);
		

		vbPopUp = new VBox();
		//vbPopUp.setBorder(test);
		vbPopUp.setTranslateX(-20);
		vbPopUp.setPrefWidth(200);
		vbPopUp.setAlignment(Pos.TOP_CENTER);
		vbPopUp.setSpacing(20);
		
		
		bpAddElement = new BorderPane();
		bpAddElement.setStyle("-fx-background-color: #e1e1e1;");
		bpAddElement.setPrefHeight(300);
		bpAddElement.setMaxWidth(400);
		lAddElement = new Label("Ajouter ");
		lAddElement.setFont(Font.font(40));
		bLeaveAddElement = new Button("x");
		bLeaveAddElement.setFont(Font.font(20));
		bLeaveAddElement.setTranslateX(-10);
		hbQuantity = new HBox();
		hbQuantity.setSpacing(10);
		hbQuantity.setAlignment(Pos.CENTER);
		tfQuantity = new TextField();
		tfQuantity.setPrefSize(60, 35);
		lQuantity = new Label("unité");
		bValiderAddElement = new Button("Valider");
		bValiderAddElement.setFont(Font.font(25));
		bValiderAddElement.setTranslateY(-10);
		vbAddElementTop = new VBox();
		vbAddElementRight = new VBox();
		vbAddElementCenter = new VBox();
		vbAddElementBottom = new VBox();
		vbAddElementTop.setAlignment(Pos.CENTER);
		vbAddElementRight.setAlignment(Pos.CENTER);
		vbAddElementCenter.setAlignment(Pos.CENTER);
		vbAddElementBottom.setAlignment(Pos.CENTER);
		
		
		bpAddTimer = new BorderPane();
		bpAddTimer.setStyle("-fx-background-color: #e1e1e1;");
		bpAddTimer.setPrefHeight(300);
		bpAddTimer.setMaxWidth(300);
		lAddTimer = new Label("Ajouter \n timer");
		lAddTimer.setFont(Font.font(20));
		bLeaveAddTimer = new Button("x");
		bLeaveAddTimer.setFont(Font.font(20));
		bLeaveAddTimer.setTranslateX(-10);
		vbCenterAddTimer= new VBox();
		vbCenterAddTimer.setAlignment(Pos.CENTER);
		vbCenterAddTimer.setSpacing(10);
		hbHeures = new HBox();
		hbHeures.setSpacing(10);
		hbHeures.setAlignment(Pos.CENTER);
		tfHeures = new TextField();
		tfHeures.setPrefSize(60, 35);
		lHeures = new Label("heures");
		hbMinutes = new HBox();
		hbMinutes.setSpacing(10);
		hbMinutes.setAlignment(Pos.CENTER);
		tfMinutes = new TextField();
		tfMinutes.setPrefSize(60, 35);
		lMinutes = new Label("Minutes");
		bValiderAddTimer = new Button("Valider");
		bValiderAddTimer.setFont(Font.font(25));
		bValiderAddTimer.setTranslateY(-10);
		vbAddTimerTop = new VBox();
		vbAddTimerRight = new VBox();
		vbAddTimerCenter = new VBox();
		vbAddTimerBottom = new VBox();
		vbAddTimerTop.setAlignment(Pos.CENTER);
		vbAddTimerRight.setAlignment(Pos.CENTER);
		vbAddTimerCenter.setAlignment(Pos.CENTER);
		vbAddTimerBottom.setAlignment(Pos.CENTER);
		
		hbFire = new HBox();
		//hbFire.setBorder(test);
		hbFire.setAlignment(Pos.CENTER_LEFT);
		hbFire.setSpacing(20);
		hbFire.setPrefHeight(300);
		bOnOffFire = new Button(" Allumer ");
		bOnOffFire.setFont(Font.font(20));
		bOnOffFire.setPrefSize(200, 80);
		colorButton(bOnOffFire);
		lPowerFire = new Label("0");
		lPowerFire.setFont(Font.font(30));
		lPowerFire.setTextFill(Paint.valueOf("#ffffff"));
		lUniteFire = new Label(" W");
		lUniteFire.setFont(Font.font(30));
		lUniteFire.setTextFill(Paint.valueOf("#ffffff"));

		vbIngrediens = new VBox();
		//vbIngrediens.setBorder(test);
		vbIngrediens.setPrefWidth(600);
		vbIngrediens.setAlignment(Pos.TOP_CENTER);
		lContenu = new Label("Contenu de la cuve");
		lContenu.setFont(Font.font(30));
		lContenu.setTextFill(Paint.valueOf("#ffffff"));
		vbContenu = new VBox();
		vbContenu.setAlignment(Pos.TOP_LEFT);
		vbContenu.setTranslateX(70);

	}

	// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
		
		vbButtons.getChildren().addAll(hbButtonsL1, hbButtonsL2);
		vbButtonsAndFire.getChildren().addAll(vbButtons, hbFire);
		vbIngrediens.getChildren().addAll(lContenu,vbContenu);
		hvManagementBottom.getChildren().addAll(vbButtonsAndFire,vbPopUp);
		vbManagement.getChildren().addAll(bpTimer, hvManagementBottom);
		
		hbButtonsSettings.getChildren().add(bInfo);
		bpTop.setLeft(hbButtonsSettings);
		message.setFont(Font.font(25));
		message.setTextFill(Paint.valueOf("#ffffff"));
		message.setTranslateY(-20);
		bpTop.setRight(lTimer);
		
		bpTimer.setRight(vbTimer);
		vbTimer.getChildren().add(bAddAlert);
		bpTimer.setLeft( vbIngrediens);
		
		//vbTankTest.getChildren().addAll(vbTankTop, lTemperature, vbTankBottom);
		vbTank.getChildren().addAll(message, spTank/*, vbFire*/);
		
		spTank.getChildren().addAll(ivTank, lTemperature);

		hbTime.getChildren().addAll(lTemps, speedOne, speedFour, speedSixteen, speedSixtyFour, speedOneHundredTwentyEight,speedFiveThousand);
		
		//vbTemperature.getChildren().addAll(lFireTemperature, sTemperature);
		
		
		hbQuantity.getChildren().addAll(tfQuantity,lQuantity);
		vbAddElementTop.getChildren().add(lAddElement);
		vbAddElementRight.getChildren().add(bLeaveAddElement);
		vbAddElementCenter.getChildren().add(hbQuantity);
		vbAddElementBottom.getChildren().add(bValiderAddElement);
		bpAddElement.setTop(vbAddElementTop);
		bpAddElement.setRight(vbAddElementRight);
		bpAddElement.setCenter(vbAddElementCenter);
		bpAddElement.setBottom(vbAddElementBottom);
		
		hbHeures.getChildren().addAll(tfHeures,lHeures);
		hbMinutes.getChildren().addAll(tfMinutes,lMinutes);
		vbAddTimerTop.getChildren().add(lAddTimer);
		vbAddTimerRight.getChildren().add(bLeaveAddTimer);
		vbAddTimerCenter.getChildren().addAll(hbHeures,hbMinutes);
		vbAddTimerBottom.getChildren().add(bValiderAddTimer);
		bpAddTimer.setTop(vbAddTimerTop);
		bpAddTimer.setRight(vbAddTimerRight);
		bpAddTimer.setCenter(vbAddTimerCenter);
		bpAddTimer.setBottom(vbAddTimerBottom);
		
		hbFire.getChildren().addAll(bOnOffFire, lPowerFire, lUniteFire);
		
		
	}

	// On definit l'emplacement des boites dans l'interface
	private void placementContainersInPane() {

		// ON ASSIGNE A CHAQUE BOX LEUR EMPLACEMENT
		this.setTop(bpTop);
		//this.setLeft(vbTemperature);
		this.setCenter(vbTank);
		this.setRight(vbManagement);
		this.setBottom(hbTime);
	}

	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	private void changeMessage(String s) {
		message.setText(s);
	}
	
	
	private void configureButtons() {
		
		bLeaveAddTimer.setOnAction((event) -> {
			hidePopUp();
			tfHeures.setText("");
			tfMinutes.setText("");
		});
		
		bValiderAddTimer.setOnAction((event) -> {
			addAlerte(tfHeures.getText(), tfMinutes.getText());
			hidePopUp();
			tfHeures.setText("");
			tfMinutes.setText("");
		});
		
		bLeaveAddElement.setOnAction((event) -> {
			hidePopUp();
		});
		
		bValiderAddElement.setOnAction((event) -> {
			hidePopUp();
		});
		bInfo.setOnAction((event) -> {
            cDialogue.afficherEcran(EcransApplication.INFORMATION);
        });
		
		bAddAlert.setOnAction((event) -> {
			showAddTimer();
		});
		colorButton(bAddAlert);
		
		bOnOffFire.setOnAction((event) -> {
			cDialogue.activerChauffage();
			onOffFire();
		});
		

	//	colorButton(bSettings);
		
		for(int i = 1; i < hbTime.getChildren().size(); i++) {
			Button speed = (Button) hbTime.getChildren().get(i);
			speed.setOnAction((event) -> {
				String speedValue = speed.getText().substring(1);
				cDialogue.changerVitesse(speedValue);
			});
		}
	}
	
	public void onOffFire() {
		Label chaud = new Label("CHAUD");
		chaud.setTextFill(Paint.valueOf("#ffffff"));
		if (vbFire.getChildren().size() == 0) {
			vbFire.getChildren().add(chaud);
			fireOn = true;
			powerFire = 0;
			lPowerFire.setText("3000");

			lUniteFire.setText(" W");
		} else {
			vbFire.getChildren().remove(0);
			lPowerFire.setText("0");
			lUniteFire.setText(" W");
			fireOn = false;
		}
	}
	
	public void addAlerte(String heures, String minutes) {
		if (vbTimer.getChildren().size() == 7){
			Label message = new Label("vous ne pouvez plus ajouter de timer");
			message.setTextFill(Paint.valueOf("#ffffff"));
			vbTimer.getChildren().add(message);
		}
		else if (vbTimer.getChildren().size() < 8) {

			VBox box = new VBox();
			// contours
			Label nom = new Label("nouvelle alerte");
			Label temps = new Label();
			if (heures != "") temps.setText(heures + " : "); else temps.setText("00 : ");
			if (minutes != "") temps.setText(temps.getText() + minutes); else temps.setText(temps.getText() + "00");

			box.getChildren().addAll(nom, temps);
			box.setAlignment(Pos.TOP_RIGHT);
			box.setBorder(border);
			box.setMaxSize(100, 50);
			
			box.setStyle("-fx-background-color: #e1e1e1;");
			box.setTranslateX(-4);

			vbTimer.getChildren().add(box);
			

		}
	}
	
	
	public static void changeTemperature(int t) {
		lTemperature.setText(t + " °C");
	}
	
	public static void actualiserTemperature() {
		changeTemperature(20);
	}
	
	public static void addButton(HashMap HM) {   //ImageView pour la suite
		Iterator iterator = HM.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            String IngredientNom = (String) me2.getKey();
            Ingredient Ingredient = (Ingredient) me2.getValue(); 
             
            Button button = new Button("Ajouter " + IngredientNom);
            button.setPrefSize(100, 100);
         	button.setOnAction((event) -> {	
         			cDialogue.ajouterIngredientCuve(Ingredient);
         			for(int i = 0; i < hbButtonsL1.getChildren().size(); i++) {
         				Button b = (Button) hbButtonsL1.getChildren().get(i);
         				if(b.getText() == button.getText()) {
         					hbButtonsL1.getChildren().remove(button);
         				}
         			}
         			for(int i = 0; i < hbButtonsL2.getChildren().size(); i++) {
         				Button b = (Button) hbButtonsL2.getChildren().get(i);
         				if(b.getText() == button.getText()) {
         					hbButtonsL2.getChildren().remove(button);
         				}
         			}
         			addIngredientCuve(Ingredient.getKg(),"kg", Ingredient.getClass().getSimpleName());
         	});
         		
         	if (hbButtonsL1.getChildren().size() > hbButtonsL2.getChildren().size()) {
         		hbButtonsL2.getChildren().add(button);
         	} else {
         		hbButtonsL1.getChildren().add(button);
         	}
         	colorButton(button);
        } 
	}
	
	public void buttonSpeed(Button button) {
		
	}
	
	/* public void addButton(Button button) {
		button.setPrefSize(100, 100);
		
		button.setOnAction((event) -> {
			System.out.println("test");
		});
		
		if (hbButtonsL1.getChildren().size() > hbButtonsL2.getChildren().size()) {
			hbButtonsL2.getChildren().add(button);
		} else {
			hbButtonsL1.getChildren().add(button);
		}
		
		colorButton(button);
	} */
	
private void showAddElement(String element) {
	if (vbPopUp.getChildren().size() == 0) {
		vbPopUp.getChildren().add(bpAddElement);
		lAddElement.setText(element);
		
		
	}
}

private void hidePopUp() {
	vbPopUp.getChildren().remove(0);
}

private void showAddTimer() {
if (vbPopUp.getChildren().size() == 0) 
	vbPopUp.getChildren().add(bpAddTimer);
}

private static void addIngredientCuve(int mesure, String unite, String ingredient) {
	Label lIngredient = new Label(ingredient + " : " + mesure + " " + unite);
	lIngredient.setFont(Font.font(20));
	lIngredient.setTextFill(Paint.valueOf("#ffffff"));
	vbContenu.getChildren().add(lIngredient);
}

	
private static void colorButton(Button button) {
		button.setStyle("-fx-background-color: #e1e1e1;");
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #f5f5f5;");
            }
        });

		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #e1e1e1;");
            }
        });
		
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d2d2d2;");
            }
        });
        
	}
		

	public EcranSimulation(ControleurDialogue cDialogue2) throws FileNotFoundException {

		cDialogue = cDialogue2;

		createWidgets();
		initBackground();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		actualizeTemp("0");

		EcranSimulation.afficherMessage("");
		

		this.setVisible(false);
		cDialogue.enregistrerEcran(nameScreen, this);

	}
	
	public static void finFermentation() {
		cDialogue.afficherEcran(EcransApplication.RESUME);
	}
	
	public static void actualizeTemp(String temp) {
		
		lTemperature.setText(temp);
	}
	
	public static void actualiserTimer(String timer) {
		lTimer.setText(timer);	
	}
	
	public static String getTimer() {
		return lTimer.getText();
	}




	public static void actualiserPuissance(int puissance) {
		
		if (puissance == 1) {
			lPowerFire.setText("0");//refroidisement
		} else {
			lPowerFire.setText("" + puissance);
		}
		
	}
	
	public static void afficherMessage(String message) {
		EcranSimulation.message.setText(message);
	}

}
