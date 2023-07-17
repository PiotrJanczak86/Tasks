package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoardsTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", false);

        List<TrelloListDto> lists1 = new ArrayList<>();
        lists1.add(trelloListDto1);

        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "list 3", false);

        List<TrelloListDto> lists2 = new ArrayList<>();
        lists2.add(trelloListDto2);
        lists2.add(trelloListDto3);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board 1", lists1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board 2", lists2);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);

        //When
        List<TrelloBoard> resultTrelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        List<TrelloList> resultList1 = resultTrelloBoards.get(0).getLists();
        List<TrelloList> resultList2 = resultTrelloBoards.get(1).getLists();

        //Then
        assertEquals(resultTrelloBoards.size(), 2);
        assertEquals(1, resultList1.size());
        assertEquals(2, resultList2.size());
        assertEquals("1",resultList1.get(0).getId());
        assertEquals("list 1", resultList1.get(0).getName());
        assertFalse(resultList1.get(0).isClosed());
        assertEquals("2",resultList2.get(0).getId());
        assertEquals("list 2", resultList2.get(0).getName());
        assertFalse(resultList2.get(0).isClosed());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1", "list 1", false);

        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);

        TrelloList trelloList2 = new TrelloList("2", "list 2", false);
        TrelloList trelloList3 = new TrelloList("3", "list 3", false);

        List<TrelloList> lists2 = new ArrayList<>();
        lists2.add(trelloList2);
        lists2.add(trelloList3);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board 1", lists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board 2", lists2);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> resultTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        List<TrelloListDto> resultList1 = resultTrelloBoardsDto.get(0).getLists();
        List<TrelloListDto> resultList2 = resultTrelloBoardsDto.get(1).getLists();

        //Then
        assertEquals(resultTrelloBoardsDto.size(), 2);
        assertEquals(resultList1.size(), 1);
        assertEquals(resultList2.size(), 2);
        assertEquals("1",resultList1.get(0).getId());
        assertEquals("list 1", resultList1.get(0).getName());
        assertFalse(resultList1.get(0).isClosed());
        assertEquals("2",resultList2.get(0).getId());
        assertEquals("list 2", resultList2.get(0).getName());
        assertFalse(resultList2.get(0).isClosed());
    }

    @Test
    void mapToListTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", false);

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto1);
        lists.add(trelloListDto2);

        //When
        List<TrelloList> resultTrelloLists = trelloMapper.mapToList(lists);
        TrelloList resultTrelloList1 = resultTrelloLists.get(0);
        TrelloList resultTrelloList2 = resultTrelloLists.get(1);

        //Then
        assertEquals(resultTrelloLists.size(), 2);
        assertEquals("1",resultTrelloList1.getId());
        assertEquals("list 1",resultTrelloList1.getName());
        assertFalse(resultTrelloList1.isClosed());
        assertEquals("2", resultTrelloList2.getId());
        assertEquals("list 2", resultTrelloList2.getName());
        assertFalse(resultTrelloList2.isClosed());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "list 2", false);

        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);

        //When
        List<TrelloListDto> resultTrelloListsDto = trelloMapper.mapToListDto(lists);
        TrelloListDto resultTrelloListDto1 = resultTrelloListsDto.get(0);
        TrelloListDto resultTrelloListDto2 = resultTrelloListsDto.get(1);

        //Then
        assertEquals(resultTrelloListsDto.size(), 2);
        assertEquals("1", resultTrelloListDto1.getId());
        assertEquals("list 1", resultTrelloListDto1.getName());
        assertFalse(resultTrelloListDto1.isClosed());
        assertEquals("2", resultTrelloListDto2.getId());
        assertEquals("list 2", resultTrelloListDto2.getName());
        assertFalse(resultTrelloListDto2.isClosed());
    }

    @Test
    void mapToCardDtoTest() {

        //Given
        TrelloCard trelloCard = new TrelloCard("card 1", "description 1", "top", "1");

        //When
        TrelloCardDto resultTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card 1", resultTrelloCardDto.getName());
        assertEquals("description 1", resultTrelloCardDto.getDescription());
        assertEquals("top", resultTrelloCardDto.getPos());
        assertEquals("1", resultTrelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card 1", "description 1", "top", "1");

        //When
        TrelloCard resultTrelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card 1", resultTrelloCard.getName());
        assertEquals("description 1", resultTrelloCard.getDescription());
        assertEquals("top", resultTrelloCard.getPos());
        assertEquals("1", resultTrelloCard.getListId());
    }
}