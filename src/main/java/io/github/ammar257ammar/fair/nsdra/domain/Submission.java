package io.github.ammar257ammar.fair.nsdra.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "nsdra_submission")
public class Submission {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="submission_id")
  private Long submissionId;
  
  @NaturalId
  @NotNull
  @Column(length = 48, nullable = false, name="submission_uuid")
  private String uuid;
  
  @Column(length = 1024, nullable = false, name="submission_url")
  private String url;

  @Column(length = 48, nullable = false, name="submission_timestamp")
  private String submissionTimestamp;

  @Column(length = 48, nullable = false, name="submission_scrape_type")
  private String type;

  @Column(length = 48, nullable = false, name="submission_status")
  private String status;

  @OneToMany(cascade = CascadeType.ALL, 
      orphanRemoval = true, 
      mappedBy = "submission",
      fetch = FetchType.LAZY
     )
  private List<MaturityIndicatorAssessment> miList = new ArrayList<MaturityIndicatorAssessment>();

  public Submission() {
  }

  public Submission(Long submissionId, String url, String submissionTimestamp, String type, String uuid) {
    super();
    this.submissionId = submissionId;
    this.url = url;
    this.submissionTimestamp = submissionTimestamp;
    this.type = type;
    this.uuid = uuid;
  }

  @JsonProperty("id")
  public Long getSubmissionId() {
    return submissionId;
  }
  @JsonIgnore
  public void setSubmissionId(Long submissionId) {
    this.submissionId = submissionId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSubmissionTimestamp() {
    return submissionTimestamp;
  }

  public void setSubmissionTimestamp(String submissionTimestamp) {
    this.submissionTimestamp = submissionTimestamp;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonIgnore
  public void setStatus(String status) {
    this.status = status;
  }

  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }

  @JsonIgnore
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public List<MaturityIndicatorAssessment> getMiList() {
    return miList;
  }

  public void setMiList(List<MaturityIndicatorAssessment> miList) {
    this.miList = miList;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((submissionTimestamp == null) ? 0 : submissionTimestamp.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    Submission other = (Submission) obj;
    if (submissionTimestamp == null) {
      if (other.submissionTimestamp != null)
        return false;
    } else if (!submissionTimestamp.equals(other.submissionTimestamp))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
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
    return "Submission [url=" + url + ", submissionTimestamp="
        + submissionTimestamp + ", type=" + type + ", status=" + status + "]";
  }
}
