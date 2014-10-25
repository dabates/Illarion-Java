/*
 * This file is part of the Illarion project.
 *
 * Copyright © 2014 - Illarion e.V.
 *
 * Illarion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Illarion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package illarion.client.net.server;

import illarion.client.net.CommandList;
import illarion.client.net.annotations.ReplyMessage;
import illarion.common.net.NetCommReader;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * Servermessage: Magic flags of the player character (
 * {@link illarion.client.net.CommandList#MSG_MAGIC_FLAG}).
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 * @author Nop
 */
@ReplyMessage(replyId = CommandList.MSG_MAGIC_FLAG)
public final class MagicFlagMsg extends AbstractReply {
    /**
     * Flags of the magic that are available. So the runes a character is
     * allowed to use.
     */
    private long flags;

    /**
     * Type of magic that is used. Such a magician, druid, bard, priest.
     */
    private short type;

    /**
     * Decode the magic flags data the receiver got and prepare it for the
     * execution.
     *
     * @param reader the receiver that got the data from the server that needs to be decoded
     * @throws IOException thrown in case there was not enough data received to decode the full message
     */
    @Override
    public void decode(@Nonnull NetCommReader reader) throws IOException {
        type = reader.readUByte();
        flags = reader.readUInt();
    }

    /**
     * Execute the magic flags message and send the decoded data to the rest of the client.
     */
    @Override
    public void executeUpdate() {
        // Gui.getInstance().getSpellBook().updateMagic(type, flags);
    }

    /**
     * Get the data of this magic flag message as string.
     *
     * @return the string that contains the values that were decoded for this
     * message
     */
    @Nonnull
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return toString("Type: " + type + " Flags: " + flags);
    }
}
