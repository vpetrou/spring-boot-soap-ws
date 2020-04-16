def getNickname(){
	def letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	def numbers = "1234567890";
	return generateRandomChars(letters, 4) + generateRandomChars(numbers, 4) 
}
/*
 * @author	Vasilis Petrou <petrou82@gmail.com>
 * @param		candidateChars a string with all the possible characters to generate a random sub-string
 * @param		length an integer to define the number of random characters to be returned 
 * @return	a random sub-string based on the candidateChars with specific length
 */
def generateRandomChars(String candidateChars, int length) {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
        sb.append(candidateChars.charAt(random.nextInt(candidateChars
                .length())));
    }
    return sb.toString();
}
