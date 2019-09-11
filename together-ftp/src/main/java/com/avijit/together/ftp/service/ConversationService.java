/*****************************************************************************
 * FILE NAME   : ConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.ftp.service;

import com.avijit.together.core.data.Constants;
import com.avijit.together.core.dto.Conversation;
import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.core.util.parser.GsonParser;
import com.avijit.together.core.ws.HttpMethod;
import com.avijit.together.core.ws.RestService;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author avijit
 *
 */
@Service("conversationService")
public class ConversationService implements IConversationService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IConversationService#findByUserId(org.
	 * springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public ResponseDTO<List<Conversation>> findByUserId(Pageable pageable, String userId) throws TogetherException {
		RestService restService = new RestService(HttpMethod.GET, false, Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_CONVERSATIONS + "/" + userId);
		try{
			restService.execute();
			if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
				String responseAsString = restService.getSuccessResponse();

				return  (ResponseDTO<List<Conversation>>) GsonParser.fromString(responseAsString, new TypeToken<ResponseDTO<List<Conversation>>>() {
				}.getType());
			}
		} catch (Exception exception) {
			return new ResponseDTO<>();
		}
		return new ResponseDTO<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IConversationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Optional<Conversation> findById(String conversationId) throws TogetherException {
		try {
			RestService restService = new RestService(HttpMethod.GET, false, Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_CONVERSATIONS + "/" + conversationId);

			restService.execute();

			if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
				String responseAsString = restService.getSuccessResponse();
				Conversation response = GsonParser.fromString(responseAsString, Conversation.class);

				return Optional.of(response);
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
	}

	@Override
	public boolean delete(String id) throws TogetherException {
		throw new UnsupportedOperationException("Delete operation is not allowed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IConversationService#isExists(java.
	 * lang.String)
	 */
	@Override
	public boolean isExists(String conversationId) throws TogetherException {
		try {
			// TODO: IMPLEMENT ME
//			return iUserRepository.existsById(UUID.fromString(userId));
			return true;
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID,
					String.format(IErrorDetails.INVALID_CONVERSATION_ID, conversationId));
		}

	}

}