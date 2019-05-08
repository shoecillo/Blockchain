package test.com.sh.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sh.app.ShBlockchain;

public class ShBlockchainTest {

	@Test
	public void testAddData() throws Exception {
		
		ShBlockchain chain = new ShBlockchain();
		chain.addData("First one");
		chain.addData("Second one");
		chain.addData("Third one");
		System.out.println(chain.prettyFormat());
		assertTrue(chain.validate());
		
	}

}
