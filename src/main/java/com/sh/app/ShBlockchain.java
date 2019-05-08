package com.sh.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.block.Block;
import com.sh.block.BlockChainList;
import com.sh.block.Crypto;

public class ShBlockchain {
	
	private List<Block> blockchain;
	private final static String FILEPATH="Wallet.dat";
	
	
	/**
	 * @param blockchain
	 */
	public ShBlockchain(List<Block> blockchain) {
		super();
		this.blockchain = new BlockChainList(blockchain);
	}

	public ShBlockchain() {
		super();
		if(new File(FILEPATH).exists()) {
			this.blockchain = readFile();
		}else {
			this.blockchain = new BlockChainList();
		}
		
	}
	
	

	public void addData(String data) {
		String prev;
		if(blockchain.size()>0) {
			prev = blockchain.get(blockchain.size()-1).getHash();
		}else {
			prev = "0";
		}
		Block bl = new Block(prev, data);
		blockchain.add(bl);
		writeFile();
	}
	
	private void writeFile() {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(FILEPATH), blockchain);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private List<Block> readFile() {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Block> ls = mapper.readValue(new File(FILEPATH), new TypeReference<List<Block>>(){});
			return new BlockChainList(ls);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String prettyFormat() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blockchain);
	}
	
	public boolean validate() {
		return Crypto.validateChain(blockchain);
	}
	
}
