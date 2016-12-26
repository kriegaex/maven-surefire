package org.apache.maven.plugin.surefire.booterclient.output;/* * Licensed to the Apache Software Foundation (ASF) under one * or more contributor license agreements.  See the NOTICE file * distributed with this work for additional information * regarding copyright ownership.  The ASF licenses this file * to you under the Apache License, Version 2.0 (the * "License"); you may not use this file except in compliance * with the License.  You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, * software distributed under the License is distributed on an * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY * KIND, either express or implied.  See the License for the * specific language governing permissions and limitations * under the License. */import org.apache.maven.plugin.surefire.report.DefaultReporterFactory;import org.apache.maven.surefire.util.internal.DumpFileUtils;import java.io.File;/** * Dumps lost commands and caused exceptions in {@link ForkClient}. * * @author <a href="mailto:tibordigana@apache.org">Tibor Digana (tibor17)</a> * @since 2.19.2 */final class LostCommandsDumpSingleton{    private static final LostCommandsDumpSingleton SINGLETON = new LostCommandsDumpSingleton();    private final String creationDate = DumpFileUtils.newFormattedDateFileName();    private LostCommandsDumpSingleton()    {    }    static LostCommandsDumpSingleton getSingleton()    {        return SINGLETON;    }    synchronized void dumpException( Throwable t, String msg, DefaultReporterFactory defaultReporterFactory )    {        File reportsDirectory = defaultReporterFactory.getReportsDirectory();        File dumpFile = new File( reportsDirectory, creationDate + ".dumpstream" );        DumpFileUtils.dumpException( t, msg, dumpFile );    }    synchronized void dumpException( Throwable t, DefaultReporterFactory defaultReporterFactory )    {        dumpException( t, null, defaultReporterFactory );    }    synchronized void dumpText( String msg, DefaultReporterFactory defaultReporterFactory )    {        File reportsDirectory = defaultReporterFactory.getReportsDirectory();        File dumpFile = new File( reportsDirectory, creationDate + ".dumpstream" );        DumpFileUtils.dumpText( msg, dumpFile );    }}