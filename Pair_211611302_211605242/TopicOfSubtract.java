
public class TopicOfSubtract extends Topic {
	// 生成一年级减法运算的题目 A-B
	// A应该大于B
	public String createTopic() {
		// 为方便B的取值，将A的取值范围设置成 [5-9] 和 [10 20 30 ....90]
		int randomA = 0;
		int randomB = 0;
		//当 true时，生成 [5-9], false时，生成 [10 20 30 ....90]
		if (random.nextBoolean()) {
			randomA = random.nextInt(5) + 5;
			randomB = random.nextInt(randomA+1);
		} else {
			randomA = 10 * (random.nextInt(9) + 1);
			randomB = 1+ random.nextInt(9);
		}
		return randomA + " - " + randomB;
	}

}
