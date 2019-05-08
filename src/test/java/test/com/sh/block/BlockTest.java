package test.com.sh.block;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.sh.block.Block;
import com.sh.block.BlockChainList;
import com.sh.block.Crypto;

public class BlockTest {
	
	@Test
	public void blockTest() {
		
		Block genesis = new Block("0", "Genesis Block");
		System.out.println(genesis.getHash());
		Block secondBlock = new Block(genesis.getHash(), "Second Block");
		System.out.println(secondBlock.getHash());
		
	}
	
	
	@Test
	public void miningTest() {
		
		List<Block> blockchain = new BlockChainList();
		Block genesis = new Block("0", "Genesis Block");
		blockchain.add(genesis);
		
		Block secondBlock = new Block(genesis.getHash(), "Second Block");
		blockchain.add(secondBlock);
		
		Block thirdBlock = new Block(secondBlock.getHash(), "Third Block");
		blockchain.add(thirdBlock);
		
		
		boolean res = Crypto.validateChain(blockchain);
		assertTrue(res);
	}
	
	
}
