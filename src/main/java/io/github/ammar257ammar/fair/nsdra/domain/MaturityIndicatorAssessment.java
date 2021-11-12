package io.github.ammar257ammar.fair.nsdra.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "nsdra_mi_assessment")
public class MaturityIndicatorAssessment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="mi_assessment_id")
  private Long miAssessmentId;

  @Column(length = 256, nullable = false, name="mi_id")
  private String miId;

  @Column(length = 1024, nullable = false, name="mi_url")
  private String url;

  @Column(length = 256, nullable = false, name="mi_title")
  private String title;

  @Column(length = 256, nullable = false, name="mi_variable")
  private String variable;

  @Column(length = 256, nullable = false, name="mi_comment")
  private String comment;

  @Column(length = 256, nullable = false, name="mi_status")
  private String status;
  
  @Column(length = 256, nullable = false, name="mi_list_id")
  private String listId;

  @Column(length = 256, nullable = false, name="mi_list_title")
  private String listTitle;

  @Column(length = 256, nullable = false, name="mi_list_reference_url")
  private String listRefernceUrl;
  
  @ManyToOne
  @JoinColumn(name = "submission_id")
  @JsonIgnore
  private Submission submission;

  public MaturityIndicatorAssessment() { }

  public MaturityIndicatorAssessment(Long miAssessmentId, String miId, String url, String title,
      String variable, String comment, String status, String listId,
      String listTitle, String listRefernceUrl) {
    super();
    this.miAssessmentId = miAssessmentId;
    this.miId = miId;
    this.url = url;
    this.title = title;
    this.variable = variable;
    this.comment = comment;
    this.status = status;
    this.listId = listId;
    this.listTitle = listTitle;
    this.listRefernceUrl = listRefernceUrl;
  }

  @JsonProperty("mi_assessment_id")
  public Long getMiAssessmentId() {
    return miAssessmentId;
  }
  
  @JsonIgnore
  public void setMiAssessmentId(Long miAssessmentId) {
    this.miAssessmentId = miAssessmentId;
  }
 
  public String getMiId() {
    return miId;
  }

  public void setMiId(String miId) {
    this.miId = miId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonIgnore
  public void setStatus(String status) {
    this.status = status;
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public String getListTitle() {
    return listTitle;
  }

  public void setListTitle(String listTitle) {
    this.listTitle = listTitle;
  }

  public String getListRefernceUrl() {
    return listRefernceUrl;
  }

  public void setListRefernceUrl(String listRefernceUrl) {
    this.listRefernceUrl = listRefernceUrl;
  }

  public Submission getSubmission() {
    return submission;
  }

  public void setSubmission(Submission submission) {
    this.submission = submission;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((comment == null) ? 0 : comment.hashCode());
    result = prime * result + ((miId == null) ? 0 : miId.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((url == null) ? 0 : url.hashCode());
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
    MaturityIndicatorAssessment other = (MaturityIndicatorAssessment) obj;
    if (comment == null) {
      if (other.comment != null)
        return false;
    } else if (!comment.equals(other.comment))
      return false;
    if (miId == null) {
      if (other.miId != null)
        return false;
    } else if (!miId.equals(other.miId))
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (url == null) {
      if (other.url != null)
        return false;
    } else if (!url.equals(other.url))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MaturityIndicator [id=" + miAssessmentId + ", url=" + url + ", title=" + title
        + ", variable=" + variable + ", comment=" + comment + ", status="
        + status + "]";
  }

}