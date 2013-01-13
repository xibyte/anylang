package mlang;

public class ScanUtils {

	public static boolean sequenceDetected(ScannerWrapper scanner,
			CharSequence sequence) {
		for (int i= 0; i < sequence.length(); i++) {
			int c = scanner.read();
			if (c != sequence.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
}
