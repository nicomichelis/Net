package structs;

import java.io.Serializable;

public class Value implements Serializable {
	private static final long serialVersionUID = 0;
	public int value;
	public int time;
	
	public Value() {
		this.value = 0;
		this.time = 0;
	}
	
	public Value(int value) {
		this.value = value;
		this.time = 0;
	}
	
	public Value(int value, int time) {
		this.value = value;
		this.time = time;
	}
	
	public void Copy(Value value){
		this.value = value.value;
		this.time = value.time;
	}
	
	public void incrementTime() {
		this.time++;
	}
	
	public void newValue(int val) {
		this.value = val;
		incrementTime();
	}
}
