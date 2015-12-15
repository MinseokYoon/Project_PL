package kr.or.kosta.pl.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review implements Serializable{
	
	private int reviewIdx;
	private Date reviewDate;
	private String reviewWriter;
	private int reviewItem;
	private String reivewContent;
	
	private String stringDate1;
	private String stringDate2;
	
	SimpleDateFormat simpleDate1 = new SimpleDateFormat("hh:mm a");
	SimpleDateFormat simpleDate2 = new SimpleDateFormat("dd MM yyyy");
	
	public Review() {}

	public Review(int reviewIdx, Date reviewDate, String reviewWriter, int reviewItem, String reivewContent) {
		super();
		this.reviewIdx = reviewIdx;
		this.reviewDate = reviewDate;
		this.reviewWriter = reviewWriter;
		this.reviewItem = reviewItem;
		this.reivewContent = reivewContent;
		String date1 = simpleDate1.format(reviewDate);
		this.stringDate1 = date1;
		String date2 = simpleDate2.format(reviewDate);
		this.stringDate2 = date2;
		
	}

	public int getReviewIdx() {
		return reviewIdx;
	}

	public void setReviewIdx(int reviewIdx) {
		this.reviewIdx = reviewIdx;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public int getReviewItem() {
		return reviewItem;
	}

	public void setReviewItem(int reviewItem) {
		this.reviewItem = reviewItem;
	}

	public String getReivewContent() {
		return reivewContent;
	}

	public void setReivewContent(String reivewContent) {
		this.reivewContent = reivewContent;
	}

	public String getStringDate1() {
		return stringDate1;
	}

	public void setStringDate1(String stringDate1) {
		this.stringDate1 = stringDate1;
	}

	public String getStringDate2() {
		return stringDate2;
	}

	public void setStringDate2(String stirngDate2) {
		this.stringDate2 = stirngDate2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reivewContent == null) ? 0 : reivewContent.hashCode());
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + reviewIdx;
		result = prime * result + reviewItem;
		result = prime * result + ((reviewWriter == null) ? 0 : reviewWriter.hashCode());
		result = prime * result + ((simpleDate1 == null) ? 0 : simpleDate1.hashCode());
		result = prime * result + ((simpleDate2 == null) ? 0 : simpleDate2.hashCode());
		result = prime * result + ((stringDate2 == null) ? 0 : stringDate2.hashCode());
		result = prime * result + ((stringDate1 == null) ? 0 : stringDate1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (reivewContent == null) {
			if (other.reivewContent != null)
				return false;
		} else if (!reivewContent.equals(other.reivewContent))
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (reviewIdx != other.reviewIdx)
			return false;
		if (reviewItem != other.reviewItem)
			return false;
		if (reviewWriter == null) {
			if (other.reviewWriter != null)
				return false;
		} else if (!reviewWriter.equals(other.reviewWriter))
			return false;
		if (simpleDate1 == null) {
			if (other.simpleDate1 != null)
				return false;
		} else if (!simpleDate1.equals(other.simpleDate1))
			return false;
		if (simpleDate2 == null) {
			if (other.simpleDate2 != null)
				return false;
		} else if (!simpleDate2.equals(other.simpleDate2))
			return false;
		if (stringDate2 == null) {
			if (other.stringDate2 != null)
				return false;
		} else if (!stringDate2.equals(other.stringDate2))
			return false;
		if (stringDate1 == null) {
			if (other.stringDate1 != null)
				return false;
		} else if (!stringDate1.equals(other.stringDate1))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [reviewIdx=" + reviewIdx + ", reviewDate=" + reviewDate + ", reviewWriter=" + reviewWriter
				+ ", reviewItem=" + reviewItem + ", reivewContent=" + reivewContent + ", stringDate1=" + stringDate1
				+ ", stirngDate2=" + stringDate2 + ", simpleDate1=" + simpleDate1 + ", simpleDate2=" + simpleDate2
				+ "]";
	}


}
