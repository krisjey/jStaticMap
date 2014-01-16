package net.sourceforge.staticmap;

/**
 * @author kris
 *
 * staticmap implementation example by template method pattern
 */
public class StaticMapByTempletExample extends StaticMapByTemplet<String, String> {

	public StaticMapByTempletExample() {
		loadData();
		doSomthing();
	}

	// TODO if you need a another process for data then make a some method.
	private void doSomthing() {
		this.staticMap.put("newkey", "test value");
	}

	@Override
	protected int loadData() {
		// TODO read data from database and put key, value to staticMap

		// TODO here is a sample for putting data to map
		this.staticMap.put("testKey", "testValue");

		return this.staticMap.size();
	}
}
