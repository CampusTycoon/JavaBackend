package CampusTycoon;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;

import CampusTycoon.GameLogic.Map;
import CampusTycoon.GameLogic.SatisfactionMeter;
import CampusTycoon.UI.Camera;
import CampusTycoon.UI.Component;
import CampusTycoon.UI.Component.Actions;
import CampusTycoon.UI.Component.Anchor;
import CampusTycoon.UI.Drawer;
import CampusTycoon.UI.Components.Backdrop;
import CampusTycoon.UI.Components.Button;
import CampusTycoon.UI.Components.MenuText;
import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Event;
import CampusTycoon.GameLogic.Buildings.*;

public class GameUtils {
	public static Map map;
	public static Event currentEvent;
	
	public static void startGame() {
		map = new Map();
		Camera.map = map;
	}
	
	// Gets the image used for hover displays (just a semi-transparent version of the original)
	public static String getHoverImagePath(String originalImage) {
		switch (originalImage) {
			case Accommodation.defaultImage:
				// e.g. "Accommodation.png" -> "AccommodationTransparent.png"
				return Accommodation.defaultImage.replace(".png", "Transparent.png");
			case Study.defaultImage:
				return Study.defaultImage.replace(".png", "Transparent.png");
			case Cafeteria.defaultImage:
				return Cafeteria.defaultImage.replace(".png", "Transparent.png");
			case Relaxation.defaultImage:
				return Relaxation.defaultImage.replace(".png", "Transparent.png");
			default:
				System.out.print(
					"ERROR: Could not retrieve hover image for \"" + originalImage + "\"");
				return "MissingTexture.png";
		}
	}
	
	public static void createStartMenuUI() {
        Button buttonNewGame = new Button("New Game.png", 0, 90, 262, 66);
		buttonNewGame.setClickAction(Actions.OpenGameplayScreen);
        buttonNewGame.setAnchor(Anchor.Centre);

        Button buttonLeaderboard = new Button("Leaderboard.png", 0, 20, 262, 66);
        buttonLeaderboard.setAnchor(Anchor.Centre);

        Button buttonSettings = new Button("Settings.png", 0, -50, 262, 66);
        buttonSettings.setAnchor(Anchor.Centre);


        List<Component> startScreenButtonList = Arrays.asList(
			buttonNewGame, 
			buttonLeaderboard, 
			buttonSettings);
		
		// Add all buttons to the drawQueue
		for (Component button : startScreenButtonList) {
			// All added to layer '1' (generally on top of most other UI elements)
			Drawer.add(1, button);
		}
		
		// Add all buttons to the InputHandler to allow for interaction handling
		// (Allows buttons to be clicked and things to actually happen)
		InputHandler.add(startScreenButtonList);
	}
	
	public static void createGameplayUI() {
		Button buttonAccommodation = new Button("Buildings\\Accommodation.png", -250, 10, 90, 66);
		buttonAccommodation.setClickAction(Actions.ToggleAccommodationBuilding);
		buttonAccommodation.setAnchor(Anchor.BottomCentre);

        Button buttonStudy = new Button("Buildings\\Study.png", -150, 10, 90, 66);
		buttonStudy.setClickAction(Actions.ToggleStudyBuilding);
        buttonStudy.setAnchor(Anchor.BottomCentre);

        Button buttonCafe = new Button("Buildings\\Cafeteria.png", -50, 10, 90, 66);
		buttonCafe.setClickAction(Actions.ToggleCafeteriaBuilding);
        buttonCafe.setAnchor(Anchor.BottomCentre);
    
        Button buttonRelax = new Button("Buildings\\Relaxation.png", 50, 10, 90, 66);
		buttonRelax.setClickAction(Actions.ToggleRelaxationBuilding);
        buttonRelax.setAnchor(Anchor.BottomCentre);

        Button buttonPH5 = new Button("Placeholder.png", 150, 10, 90, 66);
        buttonPH5.setAnchor(Anchor.BottomCentre);
    
        Button buttonPH6 = new Button("Placeholder.png",250, 10, 90, 66);
        buttonPH6.setAnchor(Anchor.BottomCentre);
    
        Button buttonDollar = new Button("Dollar.png", -300, 0, 70, 66);
        buttonDollar.setAnchor(Anchor.TopCentre);

        Button buttonHouses = new Button("House.png", 0, 0, 70, 66);
        buttonHouses.setAnchor(Anchor.TopCentre);

        Button buttonPeople = new Button("Person.png", 300, 0, 60, 66);
        buttonPeople.setAnchor(Anchor.TopCentre);

        Button notif1 = new Button("ExclamationMark.png", -12, 0, 100, 80);
        notif1.setClickAction(Actions.OpenEventPopup);
        notif1.setAnchor(Anchor.TopLeft);

        Button notif2 = new Button("QuestionMark.png", 0, 80, 80, 80);
        notif2.setClickAction(Actions.OpenEventPopup);
        notif2.setAnchor(Anchor.TopLeft);

        Button buttonSatisfaction = new Button("Satisfaction.png", 100, 10, 200, 66);
        buttonSatisfaction.setAnchor(Anchor.TopRight);
			
			
		List<Component> UIButtons = Arrays.asList(
			buttonAccommodation, buttonStudy, buttonCafe, buttonRelax, buttonPH5, buttonPH6, 
			notif1, notif2, buttonSatisfaction,
			buttonDollar, buttonHouses, buttonPeople);
		
		// Add all buttons to the drawQueue
		for (Component button : UIButtons) {
			// All added to layer '1' (generally on top of most other UI elements)
			Drawer.add(1, button);
		}
		
		// Add all buttons to the InputHandler to allow for interaction handling
		InputHandler.add(UIButtons);
		
		

        MenuText satisfactionText = new MenuText("" + SatisfactionMeter.getSatisfactionScore() +"", 50, 30, 2f, 2f);
        satisfactionText.setAnchor(Anchor.TopRight);

        MenuText notifText1 = new MenuText("Notification 1", 130, 23, 1.5f, 1.5f);
		notifText1.setAnchor(Anchor.TopLeft);

        MenuText notifText2 = new MenuText ("Notification 2", 135, 105, 1.5f, 1.5f);
        notifText2.setAnchor(Anchor.TopLeft);

		MenuText buildingCounterText = new MenuText("" +BuildingCounter.getBuildingCounter() + "", 70, 25, 2f, 2f);
		buildingCounterText.setAnchor(Anchor.TopCentre);
     
		
        List<Component> textElements = Arrays.asList(satisfactionText, notifText1, notifText2, buildingCounterText);
		
		// Add all text to the drawQueue
		for (Component text : textElements) {
			// All added to layer '2' (on top of almost all other UI elements)
			Drawer.add(2, text);
		}
		// No need to add text to the InputHandler (unless you really want to be able to click on it for some reason)
	}
	
	public static void createEventPopupUI(Event event) {
        Backdrop eventScreenBackdrop = new Backdrop("Backdrop.png", 0, 30, 400, 350);
        eventScreenBackdrop.setAnchor((Anchor.Centre));
		eventScreenBackdrop.update();
		event.eventUI.elements.add(eventScreenBackdrop);
        Drawer.add(1, eventScreenBackdrop); // Rendered behind the rest of the UI
    
        Button buttonAccept = new Button("Accept.png", -130, -106, 126, 66);
        buttonAccept.setClickAction(
			Actions.CloseEventPopup, Actions.IncreaseSatisfactionScore, 4);
        buttonAccept.setAnchor(Anchor.Centre);

        Button buttonNeutral = new Button("Neutral.png", 0, -106, 126, 66);
        buttonNeutral.setClickAction(
			Actions.CloseEventPopup, Actions.DecreaseSatisfactionSccore, 1);
        buttonNeutral.setAnchor(Anchor.Centre);

        Button buttonReject = new Button("Reject.png", 130, -106, 126, 66);
        buttonReject.setClickAction(
			Actions.CloseEventPopup, Actions.DecreaseSatisfactionSccore, 5);
        buttonReject.setAnchor(Anchor.Centre);

		
        List<Component> eventChoices = Arrays.asList(buttonAccept, buttonReject, buttonNeutral);
		
		for (Component button : eventChoices) {
			// All added to layer '2' (on top of almost all other UI elements)
			button.update();
			event.eventUI.elements.add(button);
			Drawer.add(2, button);
		}
        InputHandler.add(eventChoices);
		event.eventUI.buttonElements = eventChoices;

        //MenuText eventTextTitle = new MenuText("Event 1", 0, 0, 0, 0);
        //eventTextTitle.setAnchor(Anchor.Centre);
		MenuText testText = new MenuText(event.eventText, 0, 0, 1.5f, 1.5f);
		testText.setAnchor(Anchor.Centre);
		testText.update();
		event.eventUI.elements.add(testText);
        Drawer.add(2, testText);
	}
}
