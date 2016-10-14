package net.shadowfacts.javavox.chunk;

import net.shadowfacts.javavox.util.SizeTrackingBuffer;

/**
 * @author shadowfacts
 */
public class PaletteChunk extends Chunk {

	private int[] colors;

	public PaletteChunk() {
		children = new Chunk[0];
	}

	@Override
	public void read(SizeTrackingBuffer buffer, int childrenSize) {
		for (int i = 0; i < 256; i++) {
			int color = (buffer.getByte() << 24) | (buffer.getByte() << 16) | (buffer.getByte() << 8) | buffer.getByte();
			setColor(i, color);
		}
	}

	public int getColor(int pos) {
		return colors[pos];
	}

	public int getColor(int x, int y) {
		return getColor(x + (y * 256));
	}

	public void setColor(int pos, int color) {
		colors[pos] = color;
	}

	public void setColor(int x, int y, int color) {
		setColor(x + (y * 256), color);
	}

}
