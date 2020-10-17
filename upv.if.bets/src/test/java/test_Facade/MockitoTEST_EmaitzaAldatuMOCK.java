package test_Facade;

import static org.junit.Assert.assertTrue;

import java.util.Vector;

import javax.persistence.EntityManager;

import org.junit.Test;

//import java.awt.Event;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTEST_EmaitzaAldatuMOCK {
	 DataAccess dataAccess=Mockito.mock(DataAccess.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	public void emaitzaaldatu_returnTRUE()	{

		
		try {

			Mockito.when(dataAccess.emaitzaAldatu(Mockito.any(Integer.class), Mockito.any(String.class))).thenReturn(true);
			
			boolean emaitza_aldatuda= sut.emaitzaAldatu(5555, "hau da erantzuna");
			assertTrue(emaitza_aldatuda==true);
		}	catch (Exception	e)	{
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void emaitzaaldatu_returnFALSE()	{

		
		try {

			Mockito.when(dataAccess.emaitzaAldatu(Mockito.any(Integer.class), Mockito.any(String.class))).thenReturn(false);
			
			boolean emaitza_aldatuda= sut.emaitzaAldatu(5555, "hau da erantzuna");
			assertTrue(emaitza_aldatuda==false);
		}	catch (Exception	e)	{
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	
}
