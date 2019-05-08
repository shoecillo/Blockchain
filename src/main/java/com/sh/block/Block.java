package com.sh.block;

public class Block {
	
	private String hash;
	
	private String previousHash;
	
	private long timestamp;
	
	private int nonce;
	
	private String data;
	
	
	
	/**
	 * 
	 */
	public Block() {
		super();
		
	}

	/**
	 * @param previousHash
	 * @param data
	 */
	public Block(String previousHash, String data) {
		super();
		this.previousHash = previousHash;
		this.data = data;
		this.timestamp = System.currentTimeMillis();
		this.hash = calculateHash();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public String calculateHash() {
		
		return Crypto.createHash(this.previousHash
				              .concat(Long.toString(this.timestamp))
				              .concat(Integer.toString(this.nonce)
				              .concat(data)));
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!this.hash.substring(0, difficulty).equals(target)) {
			this.nonce++;
			this.hash = calculateHash();
		}
		System.out.println("Block Mined");
	}

	public int getNonce() {
		return nonce;
	}
	
	
	
	
}
