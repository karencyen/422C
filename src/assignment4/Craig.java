package assignment4;


/*
 * Example critter
 * Do not change or submit this file.
 */
public class Craig extends Critter {
	
	@Override
	public String toString() { return "C"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Craig() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { return true; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		
		if (getEnergy() > 150) {
			Craig child = new Craig();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
		/* pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}

	public static void runStats(java.util.List<Critter> craigs) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : craigs) {
			Craig c = (Craig) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + craigs.size() + " total Craigs    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * craigs.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * craigs.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * craigs.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * craigs.size()) + "% left   ");
		System.out.println();
	}
}
