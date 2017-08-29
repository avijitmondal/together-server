/*****************************************************************************
 * FILE NAME   : ParticipantController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.Conversation;
import com.avijit.together.server.model.Participant;
import com.avijit.together.server.service.PageResource;
import com.avijit.together.server.service.ParticipantService;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/conversations/{conversation_id}/participants")
public class ParticipantController {

	@Autowired
	private ParticipantService participantService;

	/**
	 * @param participantId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public Participant findById(@PathVariable("id") String participantId) {
		Participant participant = participantService.findById(participantId);
		return participant;
	}

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Participant> findByConversationId(@PathVariable("conversation_id") String conversationId,
			Pageable pageable) {
		Page<Participant> participants = participantService.findByConversationId(pageable, conversationId);
		return new PageResource<Participant>(participants, "page", "size");
	}

	/**
	 * @param participantId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<Conversation> delete(@PathVariable("id") String participantId) {
		boolean result = participantService.delete(participantId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param participant
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<Participant> save(@RequestBody Participant participant) {
		Participant temp = participantService.save(participant);
		return new ResponseEntity<Participant>(temp, HttpStatus.CREATED);
	}
}
