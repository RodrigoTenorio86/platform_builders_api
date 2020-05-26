package br.com.PlatformBuilders.PlatformBuildersApi.erros;

import java.time.LocalDateTime;

import lombok.Getter;
@Getter
public class ValidationErrorDetails extends ErrorDetail{
	private String field;
	private String fieldMessage;
	
	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private LocalDateTime timestamp;
		private String developerMessage;
		private String field;
		private String fieldMessage;

		private Builder() {
		}

		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder field(String field) {
			this.field = field;
			return this;
		}
		
		public Builder fieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder timestamp(LocalDateTime timestamo) {
			this.timestamp = timestamo;
			return this;
		}

		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public ValidationErrorDetails build() {
			ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
			validationErrorDetails.setTitle( title) ;
			validationErrorDetails.setDetail( detail);
			validationErrorDetails.setDeveloperMessage ( developerMessage);
			validationErrorDetails.setTimestamp( timestamp);
			validationErrorDetails.setStatus (status);
			validationErrorDetails.field = field;
			validationErrorDetails.fieldMessage = fieldMessage;
			
			return validationErrorDetails;
		}
	}	
}
