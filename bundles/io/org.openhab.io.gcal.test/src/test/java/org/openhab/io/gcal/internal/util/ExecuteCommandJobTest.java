/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.io.gcal.internal.util;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author Thomas.Eichstaedt-Engelen
 */
public class ExecuteCommandJobTest {

    ExecuteCommandJob commandJob;

    @Before
    public void init() {
        commandJob = new ExecuteCommandJob();
    }

    @Test
    public void testParseCommand() {
        String[] content;

        content = commandJob.parseCommand("send ItemName value");
        Assert.assertEquals("send", content[0]);
        Assert.assertEquals("ItemName", content[1]);
        Assert.assertEquals("value", content[2]);

        content = commandJob.parseCommand("send ItemName \"value value\"");
        Assert.assertEquals("send", content[0]);
        Assert.assertEquals("ItemName", content[1]);
        Assert.assertEquals("value value", content[2]);

        content = commandJob.parseCommand("send ItemName \"125\"");
        Assert.assertEquals("send", content[0]);
        Assert.assertEquals("ItemName", content[1]);
        Assert.assertEquals("125", content[2]);

        content = commandJob.parseCommand("send ItemName 125");
        Assert.assertEquals("send", content[0]);
        Assert.assertEquals("ItemName", content[1]);
        Assert.assertEquals("125", content[2]);

        content = commandJob.parseCommand("> say(\"Hello\")");
        Assert.assertEquals(">", content[0]);
        Assert.assertEquals("say(\"Hello\")", content[1]);
    }

}
