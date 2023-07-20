package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatedTrelloCardTest {

    @Test
    public void shouldCreateTrelloCardDtoTest(){
        //Given
        TrelloDto trelloDto = new TrelloDto();
        trelloDto.setBoard(1);
        trelloDto.setCard(1);
        AttachmentsByTypeDto attachmentsByType = new AttachmentsByTypeDto();
        attachmentsByType.setTrelloDto(trelloDto);
        BadgesDto badges = new BadgesDto();
        badges.setVotes(1);
        badges.setAttachmentsByTypeDto(attachmentsByType);
        //When
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        createdTrelloCardDto.setId("1");
        createdTrelloCardDto.setName("test");
        createdTrelloCardDto.setShortUrl("test url");
        createdTrelloCardDto.setBadgesDto(badges);
        //Then
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("test", createdTrelloCardDto.getName());
        assertEquals("test url", createdTrelloCardDto.getShortUrl());
        assertEquals(1, createdTrelloCardDto.getBadgesDto().getVotes());
        assertEquals(1, createdTrelloCardDto.getBadgesDto().getAttachmentsByTypeDto().getTrelloDto().getCard());
        assertEquals(1, createdTrelloCardDto.getBadgesDto().getAttachmentsByTypeDto().getTrelloDto().getBoard());
    }
}