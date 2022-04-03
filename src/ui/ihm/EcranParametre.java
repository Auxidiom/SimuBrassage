package ui.ihm;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

import com.sun.prism.Image;

import ui.cd.ControleurDialogue;
import ui.cd.I18N;
import ui.cd.ControleurDonnees.EcransApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EcranParametre extends BorderPane {

	private ControleurDialogue cDialogue = null;
	private static final EcransApplication nomEcran = EcransApplication.PARAMETRES;
	
	
	Border border = new Border(new BorderStroke(Color.BEIGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
			new BorderWidths(1), new Insets(5)));
	
	private Label lTitre_parametres;
	private Button bRetour;
	private Button bFrancais;
	private Button bAnglais;
	private Button bDegres;
	private Button bfahrenheit;
	
	private HBox hbSeparateur;
	private HBox hbLangues;
	private HBox hbTemp;

	private VBox vbCenter;
	private VBox vbTop;
	private VBox vbBottom;

	// On crÃƒÂ©e les differents composants de l'interface (Label, Button)
	private void createWidgets() {
			lTitre_parametres = new Label("Paramètres");
			lTitre_parametres.setFont(Font.font("Impact", FontWeight.BOLD, 60));
			lTitre_parametres.textProperty().bind(I18N.createStringBinding("label.parametres"));
			lTitre_parametres.setTextFill(Color.web("#FFFFFF"));
			lTitre_parametres.setPadding(new Insets(40, 0, 0, 0));
			
			bFrancais=  new Button("Français");
			bFrancais.textProperty().bind(I18N.createStringBinding("bouton.francais"));
			bFrancais.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			bFrancais.setPrefSize(200, 50);
			bFrancais.setTextFill(Color.web("#FFFFFF"));
			bFrancais.setOnMouseClicked(event -> {definirLangue(bFrancais);});
			
			
			bAnglais= new Button("Anglais");
			bAnglais.textProperty().bind(I18N.createStringBinding("bouton.anglais"));
			bAnglais.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			bAnglais.setPrefSize(200, 50);
			bAnglais.setTextFill(Color.web("#FFFFFF"));
			bAnglais.setOnMouseClicked(event -> {definirLangue(bAnglais);});
		
			hbLangues = new HBox();
			hbLangues.setSpacing(30);
			hbLangues.setAlignment(Pos.CENTER);
			
			hbTemp = new HBox();
			hbTemp.setSpacing(30);
			hbTemp.setAlignment(Pos.CENTER);
			
			
			hbSeparateur = new HBox();
			hbSeparateur.setMaxWidth(600);
			hbSeparateur.setBorder(border);
			
			
			bDegres=  new Button("°C");
			bDegres.setPrefSize(200, 50);
			bDegres.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			bDegres.setTextFill(Color.web("#FFFFFF"));
			
			bfahrenheit =  new Button("°F");
			bfahrenheit.setPrefSize(200, 50);
			bfahrenheit.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			bfahrenheit.setTextFill(Color.web("#FFFFFF"));


			
			bRetour = new Button("Retour");
			bRetour.textProperty().bind(I18N.createStringBinding("bouton.retour"));
			bRetour.setPrefSize(200, 50);
			bRetour.setTextFill(Color.web("#FFFFFF"));
			bRetour.setFont(Font.font("Impact", FontWeight.BOLD, 20));
			
			bFrancais.setId("0");
			bAnglais.setId("1");

			

	}

		// On crÃƒÂ©e les differents emplacements de l'interface (HBox, VBox), cela
		// permet de structurer la page
	private void createContainers() {
			vbCenter = new VBox();
			vbCenter.setSpacing(100);
			
			vbTop = new VBox();
			
			vbBottom = new VBox();
			vbBottom.setPadding(new Insets(0, 10, 10, 0));
		}
		
		// On affecte les differents Label, Button dans leurs boites respectives
	private void placementWidgetsInContainers() {
			hbLangues.getChildren().addAll(bFrancais, bAnglais);
			hbTemp.getChildren().addAll(bDegres,bfahrenheit);
			
			vbCenter.getChildren().addAll(hbLangues, hbSeparateur,hbTemp);
			vbCenter.setAlignment(Pos.CENTER);
			
			vbTop.getChildren().add(lTitre_parametres);
			vbTop.setAlignment(Pos.CENTER);
			
			vbBottom.getChildren().add(bRetour);
			vbBottom.setAlignment(Pos.CENTER_RIGHT);
		}
		
	// On definit l'emplacement des boites dans l'interface
	private void placementContainersInPane() {
		this.setTop(vbTop);
		this.setCenter(vbCenter);
		this.setBottom(vbBottom);
	}
	
	private void coulorButton(Button button) {
		

		button.setStyle("-fx-background-color: #d0ac64;");
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d0ac64;");
            }
        });

		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d0ac64;");
            }
        });
		
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	button.setStyle("-fx-background-color: #d0ac64;");
            }
        });
        
	}

	
	private void configureButtons() {
		
		bRetour.setOnAction((event) -> {
			cDialogue.afficherEcran(EcransApplication.ACCUEIL);
		});
		
		coulorButton(bFrancais);
		coulorButton(bAnglais);
		coulorButton(bDegres);
		coulorButton(bfahrenheit);
		coulorButton(bRetour);
		
		  
		
	}
	
	
	private void initBackground() {
		this.setStyle("-fx-background-color: #38322b;");
	}
	
	
		
	public EcranParametre(ControleurDialogue cd) {

		cDialogue = cd;

		
		
		createWidgets();
		createContainers();
		placementWidgetsInContainers();
		placementContainersInPane();
		configureButtons();
		initBackground();
		
	

		
		

		this.setVisible(false);
		cDialogue.enregistrerEcran(nomEcran, this);

	}
	
	public void definirLangue(Labeled n) {
		List<Locale> Locales = I18N.getSupportedLocales();
		
		I18N.setLocale(Locales.get(Integer.valueOf(n.getId())));
	}

}
