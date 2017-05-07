package application;

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
		sb.append("Na Skyrim sa dobre pozerá, lokality v hre sú spracované ve¾mi precízne. Tvorcovia vymodelovali rozsiahly kus krajiny, inšpirovanej severskou mytológiou. Exteriéry vypåòajú najmä ako schodné bralá a husté lesy, kde narazíte na rôzne tábory a jaskyne. Niekedy vás zachytí metelica, cestu skomplikuje dáï èi sneh, v ktorom som však nevidel odtlaèky stôp. Interiéry budov kladú dôraz na detaily, hoci najmä krèmièky vyzerajú dos podobne. Bludiská majú správnu atmosféru, najmä kobky s trpaslièími vynálezmi. \nPostavy vyzerajú dobre, ale s jednaním je to u o nieèo horšie. Pri chôdzi niekedy pôsobia kàèovito, jednajú chladne a strojovo. Obèas sa zasekávajú a niekedy si všimnete vypadávajúce textúry. Spravidla pomáha reštart. Engine hry je zjavne vylepšenou formou Oblivionu. Vısledkom je slušnı vzh¾ad s relatívne nízkymi nárokmi na hardvér a priestor na disku. Skyrim je ve¾kolepá RPG, ale nie jediná, ktorá tento rok bojuje o priazeò hráèov. Pred nieko¾kımi mesiacmi sme sa presvedèili o kvalitách druhého Zaklínaèa. Witcher 2 zaváòa novotami a pikantné milostné romance robia hru uverite¾nejšou. Nie je to však na úkor deja a obsahu. ijúci svet je realistickı a postavy majú charizmu. Navyše je kvalitná náplò odetá v pôsobivom grafickom kabáte. \n\nThe Elder Scrolls V stavia na istotu a pridriava sa osvedèeného systému z minulosti. V zásade neprináša niè revoluèné, skôr menšie inovácie, ktoré ladia s premyslenım celkom. Osloví pútavım príbehom a ¾ahko zrozumite¾nımi princípmi. Witcher 2 je skôr hardcore RPG. Skyrim je prístupnejší širšiemu spektru hráèov a hru zvládne kadı, kto vie do ruky chyti myš èi gamepad. Skyrim je poriadne rozsiahly. Pri plnení nepovinnıch zadaní vám zaberie aj viac ako 50 hodín. Navyše po finále môete dokonèi, èo ste nestihli. Obidva tituly majú aj svoje neduhy. Witcher 2 vrcholí vlanım finálnym aktom. Skyrim nedosahuje takú håbku postáv ako Zaklínaè a je plnı chıb, vrátane zásadnej, ktorá sa tıka hlavnej dejovej línie. Za urèitıch okolností skrátka nemôete dokonèi hru. Navyše padá. Na konzolách je zas problém s uloenımi pozíciami. Horúca záplata je u našastie na ceste, take oèakávame zlepšenie. Po zhodnotení všetkıch pre a proti si netrúfam da jednoznaènú odpoveï, ktorá hra je lepšia. A ani nepokladám za nutné o tom polemizova. Kadá hra je iná, nieèím vınimoèná a obidve budú klenotom vo vašej zbierke. Skyrim je typickım kusom z dielne Bethesda. To znamená silne návyková vec, kde však musíte tolerova mnostvo chıb. Tie by vám ale nemali bráni v tom, aby ste si hru dopriali. Nezáleí na tom, èi ste hardcore hráè alebo nováèik, The Elder Scrolls V: Skryim je RPG pre kadého.\n To najlepšie, èo môete nájs pod vianoènım stromèekom.");
		
		text.setText(sb.toString());
	}

}
