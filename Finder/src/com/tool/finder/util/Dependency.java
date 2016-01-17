package com.tool.finder.util;

public class Dependency {
    public static Dependency parse(String all) {
        System.out.println(all);
        String[] words = all.split("\\\\");
        Dependency dependency = new Dependency();
        int len = words.length;
        dependency.setVersion(words[len - 2]);
        dependency.setArtifactId(words[len - 3]);
        String gid = words[0];
        for (int i = 1; i < len - 3; i++) {
            gid = gid + "." + words[i];
        }
        dependency.setGroupId(gid);
        return dependency;
    }
    
    public Dependency() {
        
    }
    
    public boolean isSame(Dependency other) {
        if (isEmpty() || other.isEmpty()) {
            return false;
        }
        return groupId.equals(other.groupId) && artifactId.equals(other.artifactId);
    }
    
    public boolean isEmpty() {
        return groupId == null || artifactId == null || version == null;
    }
    
    public String toString() {
        return String.format("%s %s %s", groupId, artifactId, version);
    }
        
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String groupId;
    private String artifactId;
    private String version;
}
