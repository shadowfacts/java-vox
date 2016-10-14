package net.shadowfacts.javavox.chunk;

import net.shadowfacts.javavox.util.SizeTrackingBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class MainChunk extends Chunk {

	@Override
	public void read(SizeTrackingBuffer buffer, int childrenSize) {
		List<Chunk> children = new ArrayList<>();

		SizeTrackingBuffer tracker = new SizeTrackingBuffer(buffer, childrenSize);

		while (!tracker.atEnd()) {
			children.add(tracker.getChunk());
		}

		this.children = children.toArray(new Chunk[children.size()]);
	}

}
