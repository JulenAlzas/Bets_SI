package test_dataaccess;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//import java.awt.Event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.*;



public class dataAcccessTEST_EmaitzaAldatu {

	static DataAccess sut=new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	static BLFacadeImplementation testBL=new BLFacadeImplementation();

	@Test
	public void test_KuotaEmaitzarekinExist() {
		System.out.println("-----------------------------");
		System.out.println("SARRERA 1:");
		try {
			
			Event ev= sut.ekintzasortu();
			Question kuotaemaitzabera_duen_galdera=sut.galderasortu_kuotaETAemaitzaBERA(ev);

			Boolean erantzuna=sut.emaitzaAldatu(kuotaemaitzabera_duen_galdera.getQuestionNumber(), "Emaitza bera");
			System.out.println("Galderari emaitza ezarria?"+erantzuna);
			assertTrue(erantzuna);
			sut.deleteKuota(kuotaemaitzabera_duen_galdera.getKuotalista().get(0).getKuotaId());
			sut.deleteQuestion(kuotaemaitzabera_duen_galdera.getQuestionNumber());
			sut.deleteEvent(ev.getEventNumber());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_DBan_ez_exist() {
		System.out.println("-------------");
		System.out.println("SARRERA 2:");
		try {
			sut.emaitzaAldatu(99999, "Ez da aldatuko");

		} catch (Exception e) {
			assertTrue(e.getMessage()==null);
			System.out.print("questionNumber hori ez da existitzen. DBan bilatzean null bueltatzen du.Errorea: ");
			System.out.println(e);
			//			e.printStackTrace();
//			fail();
		}
	}

	@Test
	public void test_1parametroa_null() {
		System.out.println("-------------");
		System.out.println("SARRERA 3:");
		try {
			
			sut.emaitzaAldatu(null,  "Ez da aldatuko");

		} catch (Exception e) {
			System.out.print("Lehenengo parametroa null izanda. Errorea: ");
			assertTrue(e.getMessage()=="Unexpected null argument");
			System.out.println(e.getMessage());
			//			e.printStackTrace();
//			fail();
		}
	}
	@Test
	public void test_2parametroa_null() {
		System.out.println("-------------");
		System.out.println("SARRERA 4:");
		try {
			Event ev= sut.ekintzasortu();
			Question kuotaemaitzabera_duen_galdera=sut.galderasortu_kuotaETAemaitzaBERA(ev);
			boolean emaitza=sut.emaitzaAldatu(kuotaemaitzabera_duen_galdera.getQuestionNumber(), null);
			System.out.println("Emaitza aldatu da 2.parametroa null izanda eta galdera hori existituz?"+emaitza);
			sut.deleteKuota(kuotaemaitzabera_duen_galdera.getKuotalista().get(0).getKuotaId());
			sut.deleteQuestion(kuotaemaitzabera_duen_galdera.getQuestionNumber());
			sut.deleteEvent(ev.getEventNumber());
		} catch (Exception e) {
			//			e.printStackTrace();
			fail();
		}
	}
	

	@Test
	public void test_KuotarikEmaitzahorrekin_NOTExist() {
		System.out.println("-------------");
		System.out.println("SARRERA 5:");
		Event ev= sut.ekintzasortu();
		Question kuota_duen_galdera=sut.galderasortu(ev);
		try {
			boolean emaitza=sut.emaitzaAldatu(kuota_duen_galdera.getQuestionNumber(), "emaitza berria");
			System.out.println("Emaitza aldatu da emaitza bera duen kuotarik izan gabe? "+emaitza);
			sut.deleteKuota(kuota_duen_galdera.getKuotalista().get(0).getKuotaId());
			sut.deleteQuestion(kuota_duen_galdera.getQuestionNumber());
			sut.deleteEvent(ev.getEventNumber());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test_Galderak_KuotarikEZesleitua() {
		System.out.println("-------------");
		System.out.println("SARRERA 6:");
		Event ev= sut.ekintzasortu();
		Question kuotarik_ezduen_galdera=sut.galderasortu_kuotagabe(ev);
		try {
			boolean emaitza=sut.emaitzaAldatu(kuotarik_ezduen_galdera.getQuestionNumber(), "emaitza berria");
			System.out.println("Emaitza aldatu da galderak kuotarik esleitua izan gabe? "+emaitza);
			sut.deleteQuestion(kuotarik_ezduen_galdera.getQuestionNumber());
			sut.deleteEvent(ev.getEventNumber());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
