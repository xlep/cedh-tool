package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.player.Player;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PodRepository extends JpaRepository<Pod, UUID> {

  List<Pod> findByTournamentIdOrderByRoundAscNameAsc(UUID tournamentId);

  @Query(
      "SELECT DISTINCT p FROM Pod p JOIN p.seats s WHERE p.tournament.id = :tournamentId AND p.round IS NOT NULL AND s.player IS NOT NULL")
  List<Pod> findByTournamentId(@Param("tournamentId") UUID tournamentId);

  @Query("SELECT MAX(p.round) FROM Pod p WHERE p.tournament.id = :tournamentId")
  Optional<Integer> findMaxRoundForTournament(@Param("tournamentId") UUID tournamentId);

  @Query("SELECT p FROM Pod p LEFT JOIN FETCH p.seats WHERE p.tournament.id = :tournamentId")
  List<Pod> findAllWithSeats(@Param("tournamentId") UUID tournamentId);

  @Query("SELECT MAX(p.round) FROM Pod p WHERE p.tournament.id = :tournamentId")
  Optional<Integer> findHighestColumnValueForRound(@Param("tournamentId") UUID tournamentId);

  @Query("""
          SELECT DISTINCT p FROM Pod p
          LEFT JOIN FETCH p.seats s
          LEFT JOIN FETCH s.player
                    WHERE p.tournament.id = :tournamentId AND p.round = :round
      """)
  List<Pod> findPodsWithSeatsAndPlayersByTournamentIdAndRound(
      @Param("tournamentId") UUID tournamentId, @Param("round") int round);


  @Query("SELECT p FROM Pod p WHERE p.id = :id")
  Optional<List<Player>> findPlayersByPodId(@Param("id") UUID podId);

  @Query("SELECT P FROM Pod P WHERE P.tournament.id = :tournamentId AND P.type = :type")
  Pod findPodByTournamentIdAndType(@Param("tournamentId") UUID tournamentId,
      @Param("type") PodType type);

  @Query("SELECT DISTINCT p FROM Pod p JOIN p.seats s "
      + "WHERE p.tournament.id = :tournamentId AND s.result = 'BYE'")
  List<Pod> findAllByePodsForTournament(@Param("tournamentId")  UUID tournamentId);
}
