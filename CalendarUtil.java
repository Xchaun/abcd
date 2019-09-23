/**
 * ����������, �ṩ�����ж�, ���ڼ��, ���ڼ���, ���ڿ�ȼ���ȹ��ܣ�����:
 * �����ж�: �ж���������Ƿ�Ϊ����, �Ƿ���true, ���򷵻�false;
 * ���ڼ��: ������������Ƿ���һ����Ч����, �Ƿ���true, ���򷵻�false;
 * ���ڼ���: �����������������ڼ�;
 * ���ڿ�ȼ���: ����������������֮������ڿ��, ����2��������1������ֵ, ���򷵻ظ�ֵ;
 * ע��: ��������֧�ֵ���ݴӹ�ԪԪ�꣨��Ԫ1�꣩��ʼ����year > 0
 * author: Cypher
 * time: 2019/03/12
 */

public class CalendarUtil {

	// �жϵ�ǰ����Ƿ�������
	public boolean isLeapYear(int year) {
		// return (year % 4 == 0 || year % 400 == 0);
		if (year < 1000 || year > 3000)
			throw new IllegalArgumentException("���������Ч! year: " + year);
		return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
	}// end IsLeapYear

	// �������������Ƿ���Ч
	public boolean dateCheck(int year, int month, int day) {
		if (year > 1000 && year < 3000 && month > 0 && month < 12 && day > 0 && day <= 31)
			return true;
		else
			return false;
	}

	// �����������������ڼ�
	public String getWeekday(int year, int month, int day) {
		if (!dateCheck(year, month, day))
			throw new IllegalArgumentException("����������Ч! year: " + year + ", month: " + month + ", day: " + day);

		if (month == 1 || month == 2) {
			month += 12;
			year--;
		}
		int weekday = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
		switch (weekday) {
		case 0:
			return ("����һ");
		case 1:
			return ("���ڶ�");
		case 2:
			return ("������");
		case 3:
			return ("������");
		case 4:
			return ("������");
		case 5:
			return ("������");
		default:
			return ("������");
		}
	}

	// �������������ڼ���һ����ĳ��ǰ������
	private int daysOfMonth(int year, int month) {
		int[] months = { 31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int days = 0;
		for (int i = 0; i < month - 1; i++) {
			days += months[i];
		}
		if (isLeapYear(year))
			days += 1;
		return days;
	}// end daysOfMonth

	// �������������ڼ���ӹ�ԪԪ�굽ĳһ��ǰ��������
	private int daysOfYear(int year) {
		int days = 0;
		days += 365 * (year - 1000);
		for (int i = 1000; i < year; ++i) {
			if (isLeapYear(i))
				days += 1;
		} // end for
		return days;
	}// end DayOfyear

	// ����������������֮��Ŀ��
	public int dateInterval(int year1, int month1, int day1, int year2, int month2, int day2) {
		if (!dateCheck(year1, month1, day1))
			throw new IllegalArgumentException("����������Ч! year1: " + year1 + ", month1: " + month1 + ", day1: " + day1);
		if (!dateCheck(year2, month2, day2))
			throw new IllegalArgumentException("����������Ч! year2: " + year2 + ", month2: " + month2 + ", day2: " + day2);
		int days1 = daysOfYear(year1) + daysOfMonth(year1, month1) + day1;
		int days2 = daysOfYear(year2) + daysOfMonth(year2, month2) + day2;
		return days2 - days1;
	}

	public static void main(String[] args) {
		CalendarUtil cutil = new CalendarUtil();
		System.out.println(cutil.isLeapYear(2019));
		System.out.println(cutil.dateCheck(2019, 3, 11));
		System.out.println(cutil.getWeekday(2019, 3, 11));
		System.out.println(cutil.dateInterval(1985, 5, 23, 2019, 3, 11));
	}
}