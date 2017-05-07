package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ShowArticleController implements Initializable {

	@FXML
	ImageView cover;
	@FXML
	Label title;
	@FXML
	Text  text;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Image img = new Image("FILE:foto1.jpg");
		cover.setImage(img);
		
		title.setText("The Elder Scrolls V: Skyrim");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Na Skyrim sa dobre pozer�, lokality v hre s� spracovan� ve�mi prec�zne. Tvorcovia vymodelovali rozsiahly kus krajiny, in�pirovanej severskou mytol�giou. Exteri�ry vyp��aj� najm� �a�ko schodn� bral� a hust� lesy, kde naraz�te na r�zne t�bory a jaskyne. Niekedy v�s zachyt� metelica, cestu skomplikuje d�� �i sneh, v ktorom som v�ak nevidel odtla�ky st�p. Interi�ry budov klad� d�raz na detaily, hoci najm� kr�mi�ky vyzeraj� dos� podobne. Bludisk� maj� spr�vnu atmosf�ru, najm� kobky s trpasli��mi vyn�lezmi. \nPostavy vyzeraj� dobre, ale s jednan�m je to u� o nie�o hor�ie. Pri ch�dzi niekedy p�sobia k��ovito, jednaj� chladne a strojovo. Ob�as sa zasek�vaj� a niekedy si v�imnete vypad�vaj�ce text�ry. Spravidla pom�ha re�tart. Engine hry je zjavne vylep�enou formou Oblivionu. V�sledkom je slu�n� vzh�ad s relat�vne n�zkymi n�rokmi na hardv�r a priestor na disku. Skyrim je ve�kolep� RPG, ale nie jedin�, ktor� tento rok bojuje o priaze� hr��ov. Pred nieko�k�mi mesiacmi sme sa presved�ili o kvalit�ch druh�ho Zakl�na�a. Witcher 2 zav��a novotami a pikantn� milostn� romance robia hru uverite�nej�ou. Nie je to v�ak na �kor deja a obsahu. �ij�ci svet je realistick� a postavy maj� charizmu. Navy�e je kvalitn� n�pl� odet� v p�sobivom grafickom kab�te. \n\nThe Elder Scrolls V stavia na istotu a pridr�iava sa osved�en�ho syst�mu z minulosti. V z�sade neprin�a ni� revolu�n�, sk�r men�ie inov�cie, ktor� ladia s premyslen�m celkom. Oslov� p�tav�m pr�behom a �ahko zrozumite�n�mi princ�pmi. Witcher 2 je sk�r hardcore RPG. Skyrim je pr�stupnej�� �ir�iemu spektru hr��ov a hru zvl�dne ka�d�, kto vie do ruky chyti� my� �i gamepad. Skyrim je poriadne rozsiahly. Pri plnen� nepovinn�ch zadan� v�m zaberie aj viac ako 50 hod�n. Navy�e po fin�le m��ete dokon�i�, �o ste nestihli. Obidva tituly maj� aj svoje neduhy. Witcher 2 vrchol� vla�n�m fin�lnym aktom. Skyrim nedosahuje tak� h�bku post�v ako Zakl�na� a je pln� ch�b, vr�tane z�sadnej, ktor� sa t�ka hlavnej dejovej l�nie. Za ur�it�ch okolnost� skr�tka nem��ete dokon�i� hru. Navy�e pad�. Na konzol�ch je zas probl�m s ulo�en�mi poz�ciami. Hor�ca z�plata je u� na��astie na ceste, tak�e o�ak�vame zlep�enie. Po zhodnoten� v�etk�ch pre a proti si netr�fam da� jednozna�n� odpove�, ktor� hra je lep�ia. A ani nepoklad�m za nutn� o tom polemizova�. Ka�d� hra je in�, nie��m v�nimo�n� a obidve bud� klenotom vo va�ej zbierke. Skyrim je typick�m kusom z dielne Bethesda. To znamen� silne n�vykov� vec, kde v�ak mus�te tolerova� mno�stvo ch�b. Tie by v�m ale nemali br�ni� v tom, aby ste si hru dopriali. Nez�le�� na tom, �i ste hardcore hr�� alebo nov��ik, The Elder Scrolls V: Skryim je RPG pre ka�d�ho.\n To najlep�ie, �o m��ete n�js� pod viano�n�m strom�ekom.");
		
		text.setText(sb.toString());
	}

}
