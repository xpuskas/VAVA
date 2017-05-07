package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ShowReviewController implements Initializable {

	@FXML
	Label title;
	@FXML
	Label rank;
	@FXML
	Label pro1;
	@FXML
	Label pro2;
	@FXML
	Label pro3;
	@FXML
	Label pro4;
	@FXML
	Label con1;
	@FXML
	Label con2;
	@FXML
	Label con3;
	@FXML
	Label con4;
	@FXML
	Text text;
	@FXML
	ImageView cover;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		title.setText("The Elder Scrolls V: Skyrim");
		rank.setText("9.5");
		
		Image img = new Image("FILE:foto.jpg");
		cover.setImage(img);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Skyrim ani nemohol vyjs v príhodnejšom èase. Je studenı november a udalosti nového prírastku v sérii The Elder Scrolls plynú na drsnom severe. Mono vás chví¾ami bude mrazi, ale urèite preijete aj horúce momenty. S drakmi toti nie sú iadne arty, ani keï sa narodíte ako hrdina s draèou dušou. V krajine sa draci neukázali u ve¾mi dlho. Nikto neveril, e ešte nejakí ijú a niektorí dokonca zapochybovali, èi vôbec jestvovali. Prílet okrídleného monštra hneï v úvode však všetkım otvoril oèi a vzbudil oprávnenı strach.\n\n Ale èo je pre domorodcov pohromou, znamená pre hlavného hrdinu spásu. Predsa len je lepšie èeli drakovi, ako sa necha sa. Èoskoro zistíte, e protivník, ktorému neèakane èelíte u krátko po štarte, nie je jedináèik. Celá krajina je náhle zamorená drakmi. Musíte vypátra, preèo tieto zabudnuté monštrá znovu povstali. Navyše sa zamotáte do mocenskıch konfliktov medzi znepriatelenımi frakciami Skyrimu. Cesta k odpovediam a desiatky hodín vzdialenému finále vedie cez rôzne mesteèká, zasneené hory a bludiská s pavúkmi a kostlivcami. O úlohy nie je núdza.\n Jedny sú dôleité, iné v zásade bezvıznamné, ale urèite sa hodia na získanie extra skúseností a odmien. Niektoré súvisia priamo s drakmi, ale budete aj pátra po nezvestnıch osobách, oslobodzova zajatcov, èi špehova a lúpi na diplomatickom veèierku. Vydáte sa po stopách stratenıch artefaktov, absolvujete skúšky do rôznych spoloèenstiev, a budete vymáha dlhy. Niekedy máte monos vo¾by. Je na vás, ku komu sa pridáte a ako vyriešite urèité problémy.\n\n\n V istıch prípadoch sa vám to môe podari aj bez násilia, najmä keï máte rozvinutú vıreènos a vyberáte priliehavé odpovede v dialógoch. Mimochodom, nebuïte prekvapení, keï sa s vami NPC nebudú chcie porozpráva, kım neukonèia konverzáciu medzi sebou. Pri postupe, najmä v bludiskách, èasto narazíte na nenároèné rébusy. Spravidla treba správne usporiada symboly na otoènıch doskách. Riešenie bıva ukryté v okolí. Zvyèajne staèí by len všímavım a dobre sa poobzera okolo seba.");
		
		text.setText(sb.toString());
	}
}
