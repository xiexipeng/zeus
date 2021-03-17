

//import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;
//import sun.jvm.hotspot.gc_implementation.parallelScavenge.ParallelScavengeHeap;
//import sun.jvm.hotspot.gc_implementation.shared.MutableSpace;
//import sun.jvm.hotspot.gc_interface.CollectedHeap;
//import sun.jvm.hotspot.memory.Universe;
//import sun.jvm.hotspot.oops.HeapPrinter;
//import sun.jvm.hotspot.oops.HeapVisitor;
//import sun.jvm.hotspot.oops.ObjectHeap;
//import sun.jvm.hotspot.runtime.VM;
//import sun.jvm.hotspot.tools.Tool;

/**
 * @Author: xiexipeng
 * @Date: 2019/6/26
 * @Time: 下午10:18
 * @Description:
 */
public class TestPrintPSPermGen
//		extends Tool
{

//	public static void main(String[] args) {
//		TestPrintPSPermGen test = new TestPrintPSPermGen();
//		test.execute(args);
//		test.stop();
//	}
//
//	@Override
//	public void run() {
//
//		VM vm = VM.getVM();
//		Universe universe = vm.getUniverse();
//		CollectedHeap heap = universe.heap();
//		puts("GC heap name: " + heap.kind());
//		if (heap instanceof ParallelScavengeHeap) {
//			ParallelScavengeHeap psHeap = (ParallelScavengeHeap) heap;
//			PSYoungGen youngGen = psHeap.youngGen();
//			MutableSpace permObjSpace = youngGen.edenSpace();
//			puts("Perm gen: [" + permObjSpace.bottom() + ", " + permObjSpace.end() + ")");
//			long permSize = 0;
//			for (VM.Flag f : VM.getVM().getCommandLineFlags()) {
//				if ("PermSize".equals(f.getName())) {
//					permSize = Long.parseLong(f.getValue());
//					break;
//				}
//			}
//			puts("PermSize: " + permSize);
//		}
//		puts();
//
//		ObjectHeap objHeap = vm.getObjectHeap();
//		HeapVisitor heapVisitor = new HeapPrinter(System.out);
//		objHeap.iterate(heapVisitor);
//	}
//
//	private static void puts() {
//		System.out.println();
//	}
//
//	private static void puts(String s) {
//		System.out.println(s);
//	}
}