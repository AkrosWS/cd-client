/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.akros.workshop.cd.client.test;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.akros.workshop.cd.client.PlayerService;
import ch.akros.workshop.cd.service.GameService;
import ch.akros.workshop.cd.service.GameTestInterface;

//@formatter:off
/**
 * To run this test in embedded wildfly you got to set
 * -Djava.util.logging.manager=org.jboss.logmanager.LogManager 
 * in run runtime configuration VM Arguments
 */
//@formatter:on

@RunWith(Arquillian.class)
public class PlayerITest {

	@Deployment(name = "server", order = 1)
	public static Archive<?> createServerTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "cd.war").addPackages(true, "ch.akros.workshop.cd.exception", "ch.akros.workshop.cd.domain")
				.addClass(ch.akros.workshop.cd.service.GameService.class).addClass(GameTestInterface.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Deployment(name = "client", order = 2)
	public static Archive<?> createClientTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "cd-client6.war")
				.addPackages(true, "ch.akros.workshop.cd.client", "ch.akros.workshop.cd.util", "ch.akros.workshop.cd.service",
						"ch.akros.workshop.cd.exception", "ch.akros.workshop.cd.domain").addClass(GameTestInterface.class)
				.deleteClass(GameService.class).addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml");
	}

	@EJB(lookup = "java:global/cd/GameService!ch.akros.workshop.cd.domain.Game")
	private GameService game;

	@Inject
	private PlayerService testee;

	@Test
	@OperateOnDeployment("client")
	public void testAutomatedSubscriptionToGame() throws Exception {
		Thread.sleep(10000L);
		Assert.assertNotNull(testee);
		Assert.assertNotNull(game);
		Assert.assertTrue("Player did not subscribe", game.didPlayerSubscribe(testee));
	}
}
