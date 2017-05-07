package controllers;

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
		sb.append("Skyrim ani nemohol vyjs� v pr�hodnej�om �ase. Je studen� november a udalosti nov�ho pr�rastku v s�rii The Elder Scrolls plyn� na drsnom severe. Mo�no v�s chv�ami bude mrazi�, ale ur�ite pre�ijete aj hor�ce momenty. S drakmi toti� nie s� �iadne �arty, ani ke� sa narod�te ako hrdina s dra�ou du�ou. V krajine sa draci neuk�zali u� ve�mi dlho. Nikto neveril, �e e�te nejak� �ij� a niektor� dokonca zapochybovali, �i v�bec jestvovali. Pr�let okr�dlen�ho mon�tra hne� v �vode v�ak v�etk�m otvoril o�i a vzbudil opr�vnen� strach.\n\n Ale �o je pre domorodcov pohromou, znamen� pre hlavn�ho hrdinu sp�su. Predsa len je lep�ie �eli� drakovi, ako sa necha� s�a�. �oskoro zist�te, �e protivn�k, ktor�mu ne�akane �el�te u� kr�tko po �tarte, nie je jedin��ik. Cel� krajina je n�hle zamoren� drakmi. Mus�te vyp�tra�, pre�o tieto zabudnut� mon�tr� znovu povstali. Navy�e sa zamot�te do mocensk�ch konfliktov medzi znepriatelen�mi frakciami Skyrimu. Cesta k odpovediam a desiatky hod�n vzdialen�mu fin�le vedie cez r�zne meste�k�, zasne�en� hory a bludisk� s pav�kmi a kostlivcami. O �lohy nie je n�dza.\n Jedny s� d�le�it�, in� v z�sade bezv�znamn�, ale ur�ite sa hodia na z�skanie extra sk�senost� a odmien. Niektor� s�visia priamo s drakmi, ale budete aj p�tra� po nezvestn�ch osob�ch, oslobodzova� zajatcov, �i �pehova� a l�pi� na diplomatickom ve�ierku. Vyd�te sa po stop�ch straten�ch artefaktov, absolvujete sk��ky do r�znych spolo�enstiev, a budete vym�ha� dlhy. Niekedy m�te mo�nos� vo�by. Je na v�s, ku komu sa prid�te a ako vyrie�ite ur�it� probl�my.\n\n\n V ist�ch pr�padoch sa v�m to m��e podari� aj bez n�silia, najm� ke� m�te rozvinut� v�re�nos� a vyber�te priliehav� odpovede v dial�goch. Mimochodom, nebu�te prekvapen�, ke� sa s vami NPC nebud� chcie� porozpr�va�, k�m neukon�ia konverz�ciu medzi sebou. Pri postupe, najm� v bludisk�ch, �asto naraz�te na nen�ro�n� r�busy. Spravidla treba spr�vne usporiada� symboly na oto�n�ch dosk�ch. Rie�enie b�va ukryt� v okol�. Zvy�ajne sta�� by� len v��mav�m a dobre sa poobzera� okolo seba.");
		
		text.setText(sb.toString());
	}
}
