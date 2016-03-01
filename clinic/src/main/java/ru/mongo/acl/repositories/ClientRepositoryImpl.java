package ru.mongo.acl.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.03.2016
 */
@Repository
public class ClientRepositoryImpl implements ClientRepository {
	private static final Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);

	@Autowired
	private JdbcTemplate template;

	@Override
	public Client save(Client s) {
		return null;
	}


	@Override
	public <S extends Client> Iterable<S> save(Iterable<S> iterable) {
		return null;
	}

	@Override
	public Client findOne(String s) {
		return null;
	}

	@Override
	public boolean exists(String s) {
		return false;
	}

	@Override
	public Iterable<Client> findAll() {
		return this.template.query("select * from client",
				new RowMapper<Client>() {
					@Override
					public Client mapRow(ResultSet resultSet, int i) throws SQLException {
						Client client = new Client();
						client.setId(resultSet.getString("id"));
						client.setLogin(resultSet.getString("login"));
						client.setPassword(resultSet.getString("password"));
						return client;
					}
				});
	}

	@Override
	public Iterable<Client> findAll(Iterable<String> iterable) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void delete(String s) {

	}

	@Override
	public void delete(Client client) {

	}

	@Override
	public void delete(Iterable<? extends Client> iterable) {

	}

	@Override
	public void deleteAll() {

	}
}
