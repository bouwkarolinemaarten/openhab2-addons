/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
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
package org.openhab.binding.ihc.internal.converters;

import static org.junit.Assert.assertEquals;

import org.eclipse.smarthome.core.library.types.DateTimeType;
import org.eclipse.smarthome.core.types.Type;
import org.junit.Test;
import org.openhab.binding.ihc.internal.ws.exeptions.ConversionException;
import org.openhab.binding.ihc.internal.ws.resourcevalues.WSResourceValue;
import org.openhab.binding.ihc.internal.ws.resourcevalues.WSTimeValue;

/**
 * Test for IHC / ELKO binding
 *
 * @author Pauli Anttila - Initial contribution
 */
public class DateTimeTypeWSTimeValueConverterTest {

    @Test
    public void testConversion() throws ConversionException {
        final DateTimeType dateTimeType = new DateTimeType("2000-12-30T13:59:30");
        WSTimeValue val = new WSTimeValue(12345);

        val = convertFromOHType(val, dateTimeType, null);
        assertEquals(12345, val.getResourceID());
        assertEquals(13, val.getHours());
        assertEquals(59, val.getMinutes());
        assertEquals(30, val.getSeconds());

        DateTimeType type = convertFromResourceValue(val, null);
        assertEquals(dateTimeType.format("HH:mm:ss"), type.format("HH:mm:ss"));
    }

    private WSTimeValue convertFromOHType(WSTimeValue IHCvalue, Type OHval,
            ConverterAdditionalInfo converterAdditionalInfo) throws ConversionException {
        Converter<WSResourceValue, Type> converter = ConverterFactory.getInstance().getConverter(IHCvalue.getClass(),
                DateTimeType.class);
        return (WSTimeValue) converter.convertFromOHType(OHval, IHCvalue, converterAdditionalInfo);
    }

    private DateTimeType convertFromResourceValue(WSTimeValue IHCvalue, ConverterAdditionalInfo converterAdditionalInfo)
            throws ConversionException {
        Converter<WSResourceValue, Type> converter = ConverterFactory.getInstance().getConverter(IHCvalue.getClass(),
                DateTimeType.class);
        return (DateTimeType) converter.convertFromResourceValue(IHCvalue, converterAdditionalInfo);
    }
}
