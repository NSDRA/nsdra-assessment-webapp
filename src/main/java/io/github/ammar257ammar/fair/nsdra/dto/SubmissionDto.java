package io.github.ammar257ammar.fair.nsdra.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "url", "submissionTimestamp", "type", "status", "presistResults", "uuid", "miList" })
public class SubmissionDto {
  
  @JsonProperty("uuid")
  private String uuid;
  
  @JsonProperty("url")
  private String url;

  @JsonProperty("submissionTimestamp")
  private String submissionTimestamp;

  @JsonProperty("type")
  private String type;

  @JsonProperty("status")
  private String status;

  @JsonProperty("presistResults")
  private boolean presistResults;

  @JsonProperty("miList")
  private List<MaturityIndicatorAssessmentDto> miList = new ArrayList<MaturityIndicatorAssessmentDto>();

  public SubmissionDto() {}

  public SubmissionDto(String url, String submissionTimestamp, String type, String uuid) {
    super();
    this.url = url;
    this.submissionTimestamp = submissionTimestamp;
    this.type = type;
    this.uuid = uuid;
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

  public boolean isPresistResults() {
    return presistResults;
  }

  public void setPresistResults(boolean presistResults) {
    this.presistResults = presistResults;
  }

  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }

  @JsonIgnore
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public List<MaturityIndicatorAssessmentDto> getMiList() {
    return miList;
  }

  public void setMiList(List<MaturityIndicatorAssessmentDto> miList) {
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
    SubmissionDto other = (SubmissionDto) obj;
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
