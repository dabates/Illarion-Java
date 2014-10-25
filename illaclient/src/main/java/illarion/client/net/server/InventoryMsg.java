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
import illarion.client.world.World;
import illarion.common.net.NetCommReader;
import illarion.common.types.ItemCount;
import illarion.common.types.ItemId;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * Servermessage: Update of a inventory item ({@link CommandList#MSG_INVENTORY}).
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 * @author Nop
 */
@ReplyMessage(replyId = CommandList.MSG_INVENTORY)
public final class InventoryMsg extends AbstractReply {
    /**
     * New count of the item on the position.
     */
    private ItemCount count;

    /**
     * New ID of the item.
     */
    private ItemId itemId;

    /**
     * Position in the inventory.
     */
    private short location;

    /**
     * Decode the inventory item data the receiver got and prepare it for the execution.
     *
     * @param reader the receiver that got the data from the server that needs to be decoded
     * @throws IOException thrown in case there was not enough data received to decode the full message
     */
    @Override
    public void decode(@Nonnull NetCommReader reader) throws IOException {
        location = reader.readUByte();
        itemId = new ItemId(reader);
        count = ItemCount.getInstance(reader);
    }

    /**
     * Execute the inventory item message and send the decoded data to the rest of the client.
     */
    @Override
    public void executeUpdate() {
        World.getPlayer().getInventory().setItem(location, itemId, count);
    }

    /**
     * Get the data of this inventory item message as string.
     *
     * @return the string that contains the values that were decoded for this
     * message
     */
    @Nonnull
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return toString("pos: " + location + ' ' + itemId + ' ' + count);
    }
}
