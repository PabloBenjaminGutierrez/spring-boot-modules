package nl.jaapcoomans.demo.springbootmodules.boardgame.rest;

import java.util.List;
import java.util.UUID;

import nl.jaapcoomans.demo.springbootmodules.boardgame.domain.BoardGame;
import nl.jaapcoomans.demo.springbootmodules.boardgame.domain.BoardGameQueryRepository;
import nl.jaapcoomans.demo.springbootmodules.boardgame.domain.BoardGameService;
import nl.jaapcoomans.demo.springbootmodules.boardgame.domain.command.CreateBoardGameCommand;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameRestApi {
	private BoardGameQueryRepository boardGameQueryRepository;
	private BoardGameService boardGameService;

	public BoardGameRestApi(final BoardGameQueryRepository boardGameQueryRepository, final BoardGameService boardGameService) {
		this.boardGameQueryRepository = boardGameQueryRepository;
		this.boardGameService = boardGameService;
	}

	@GetMapping
	public List<BoardGame> getAllBoardGames() {
		return this.boardGameQueryRepository.getAllBoardGames();
	}

	@GetMapping("/{id}")
	public BoardGame getBoardGameById(@PathVariable UUID id) {
		return this.boardGameQueryRepository.findById(id)
			.orElseThrow(ResourceNotFoundException::new);
	}

	@PostMapping
	public BoardGame createNewBoardGame(@RequestBody CreateBoardGameCommand createCommand) {
		return this.boardGameService.createNewBoardGame(createCommand);
	}
}